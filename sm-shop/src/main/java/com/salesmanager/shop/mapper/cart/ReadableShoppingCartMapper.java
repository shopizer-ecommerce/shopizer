package com.salesmanager.shop.mapper.cart;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.mapper.catalog.ReadableMinimalProductMapper;
import com.salesmanager.shop.model.order.total.ReadableOrderTotal;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttribute;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttributeOption;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartAttributeOptionValue;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartItem;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableShoppingCartMapper implements Mapper<ShoppingCart, ReadableShoppingCart> {

	private static final Logger LOG = LoggerFactory.getLogger(ReadableShoppingCartMapper.class);

	@Autowired
	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Autowired
	private PricingService pricingService;

	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private ReadableMinimalProductMapper readableMinimalProductMapper;

	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableShoppingCart convert(ShoppingCart source, MerchantStore store, Language language) {
		ReadableShoppingCart destination = new ReadableShoppingCart();
		return this.merge(source, destination, store, language);
	}

	@Override
	public ReadableShoppingCart merge(ShoppingCart source, ReadableShoppingCart destination, MerchantStore store,
			Language language) {
		Validate.notNull(source, "ShoppingCart cannot be null");
		Validate.notNull(destination, "ReadableShoppingCart cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

		destination.setCode(source.getShoppingCartCode());
		int cartQuantity = 0;

		destination.setCustomer(source.getCustomerId());

		try {

			if (!StringUtils.isBlank(source.getPromoCode())) {
				Date promoDateAdded = source.getPromoAdded();// promo valid 1 day
				if (promoDateAdded == null) {
					promoDateAdded = new Date();
				}
				Instant instant = promoDateAdded.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate date = zdt.toLocalDate();
				// date added < date + 1 day
				LocalDate tomorrow = LocalDate.now().plusDays(1);
				if (date.isBefore(tomorrow)) {
					destination.setPromoCode(source.getPromoCode());
				}
			}

			Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items = source.getLineItems();

			if (items != null) {

				for (com.salesmanager.core.model.shoppingcart.ShoppingCartItem item : items) {

					ReadableShoppingCartItem shoppingCartItem = new ReadableShoppingCartItem();

					readableMinimalProductMapper.merge(item.getProduct(), shoppingCartItem, store, language);

					// ReadableProductPopulator readableProductPopulator = new
					// ReadableProductPopulator();
					// readableProductPopulator.setPricingService(pricingService);
					// readableProductPopulator.setimageUtils(imageUtils);
					// readableProductPopulator.populate(item.getProduct(), shoppingCartItem, store,
					// language);

					shoppingCartItem.setPrice(item.getItemPrice());
					shoppingCartItem.setFinalPrice(pricingService.getDisplayAmount(item.getItemPrice(), store));

					shoppingCartItem.setQuantity(item.getQuantity());

					cartQuantity = cartQuantity + item.getQuantity();

					BigDecimal subTotal = pricingService.calculatePriceQuantity(item.getItemPrice(),
							item.getQuantity());

					// calculate sub total (price * quantity)
					shoppingCartItem.setSubTotal(subTotal);

					shoppingCartItem.setDisplaySubTotal(pricingService.getDisplayAmount(subTotal, store));

					Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> attributes = item
							.getAttributes();
					if (attributes != null) {
						for (com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attribute : attributes) {

							ProductAttribute productAttribute = productAttributeService
									.getById(attribute.getProductAttributeId());

							if (productAttribute == null) {
								LOG.warn("Product attribute with ID " + attribute.getId()
										+ " not found, skipping cart attribute " + attribute.getId());
								continue;
							}

							ReadableShoppingCartAttribute cartAttribute = new ReadableShoppingCartAttribute();

							cartAttribute.setId(attribute.getId());

							ProductOption option = productAttribute.getProductOption();
							ProductOptionValue optionValue = productAttribute.getProductOptionValue();

							List<ProductOptionDescription> optionDescriptions = option.getDescriptionsSettoList();
							List<ProductOptionValueDescription> optionValueDescriptions = optionValue
									.getDescriptionsSettoList();

							String optName = null;
							String optValue = null;
							if (!CollectionUtils.isEmpty(optionDescriptions)
									&& !CollectionUtils.isEmpty(optionValueDescriptions)) {

								optName = optionDescriptions.get(0).getName();
								optValue = optionValueDescriptions.get(0).getName();

								for (ProductOptionDescription optionDescription : optionDescriptions) {
									if (optionDescription.getLanguage() != null && optionDescription.getLanguage()
											.getId().intValue() == language.getId().intValue()) {
										optName = optionDescription.getName();
										break;
									}
								}

								for (ProductOptionValueDescription optionValueDescription : optionValueDescriptions) {
									if (optionValueDescription.getLanguage() != null && optionValueDescription
											.getLanguage().getId().intValue() == language.getId().intValue()) {
										optValue = optionValueDescription.getName();
										break;
									}
								}

							}

							if (optName != null) {
								ReadableShoppingCartAttributeOption attributeOption = new ReadableShoppingCartAttributeOption();
								attributeOption.setCode(option.getCode());
								attributeOption.setId(option.getId());
								attributeOption.setName(optName);
								cartAttribute.setOption(attributeOption);
							}

							if (optValue != null) {
								ReadableShoppingCartAttributeOptionValue attributeOptionValue = new ReadableShoppingCartAttributeOptionValue();
								attributeOptionValue.setCode(optionValue.getCode());
								attributeOptionValue.setId(optionValue.getId());
								attributeOptionValue.setName(optValue);
								cartAttribute.setOptionValue(attributeOptionValue);
							}
							shoppingCartItem.getCartItemattributes().add(cartAttribute);
						}

					}
					destination.getProducts().add(shoppingCartItem);
				}
			}

			// Calculate totals using shoppingCartService
			// OrderSummary contains ShoppingCart items

			OrderSummary summary = new OrderSummary();
			List<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> productsList = new ArrayList<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
			productsList.addAll(source.getLineItems());
			summary.setProducts(productsList);

			// OrdetTotalSummary contains all calculations

			OrderTotalSummary orderSummary = shoppingCartCalculationService.calculate(source, store, language);

			if (CollectionUtils.isNotEmpty(orderSummary.getTotals())) {

				if (orderSummary.getTotals().stream()
						.filter(t -> Constants.OT_DISCOUNT_TITLE.equals(t.getOrderTotalCode())).count() == 0) {
					// no promo coupon applied
					destination.setPromoCode(null);

				}

				List<ReadableOrderTotal> totals = new ArrayList<ReadableOrderTotal>();
				for (com.salesmanager.core.model.order.OrderTotal t : orderSummary.getTotals()) {
					ReadableOrderTotal total = new ReadableOrderTotal();
					total.setCode(t.getOrderTotalCode());
					total.setValue(t.getValue());
					total.setText(t.getText());
					totals.add(total);
				}
				destination.setTotals(totals);
			}

			destination.setSubtotal(orderSummary.getSubTotal());
			destination.setDisplaySubTotal(pricingService.getDisplayAmount(orderSummary.getSubTotal(), store));

			destination.setTotal(orderSummary.getTotal());
			destination.setDisplayTotal(pricingService.getDisplayAmount(orderSummary.getTotal(), store));

			destination.setQuantity(cartQuantity);
			destination.setId(source.getId());

			if (source.getOrderId() != null) {
				destination.setOrder(source.getOrderId());
			}

		} catch (Exception e) {
			throw new ConversionRuntimeException("An error occured while converting ReadableShoppingCart", e);
		}

		return destination;
	}

}
