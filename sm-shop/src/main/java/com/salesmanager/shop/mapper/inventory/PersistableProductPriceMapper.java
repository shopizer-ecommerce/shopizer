package com.salesmanager.shop.mapper.inventory;

import static com.salesmanager.core.business.utils.NumberUtils.isPositive;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class PersistableProductPriceMapper implements Mapper<PersistableProductPrice, ProductPrice> {

	@Autowired
	private LanguageService languageService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductAvailabilityService productAvailabilityService;

	@Override
	public ProductPrice convert(PersistableProductPrice source, MerchantStore store, Language language) {
		return merge(source, new ProductPrice(), store, language);
	}

	@Override
	public ProductPrice merge(PersistableProductPrice source, ProductPrice destination, MerchantStore store,
			Language language) {

		Validate.notNull(source, "PersistableProductPrice cannot be null");
		Validate.notNull(source.getSku(), "Product sku cannot be null");

		try {
			if (destination == null) {
				destination = new ProductPrice();
			}
			
			destination.setId(source.getId());

			/**
			 * Get product availability and verify the existing br-pa-1.0.0
			 * 
			 * Cannot have multiple default price for the same product availability Default
			 * price can be edited but cannot create new default price
			 */

			ProductAvailability availability = null;

			if (isPositive(source.getProductAvailabilityId())) {
				Optional<ProductAvailability> avail = productAvailabilityService
						.getById(source.getProductAvailabilityId(), store);
				if (avail.isEmpty()) {
					throw new ConversionRuntimeException(
							"Product availability with id [" + source.getProductAvailabilityId() + "] was not found");
				}
				availability = avail.get();

			} else {

				// get an existing product availability
				List<ProductAvailability> existing = productAvailabilityService.getBySku(source.getSku(), store);

				if (!CollectionUtils.isEmpty(existing)) {
					// find default availability
					Optional<ProductAvailability> avail = existing.stream()
							.filter(a -> a.getRegion() != null && a.getRegion().equals(Constants.ALL_REGIONS))
							.findAny();
					if (avail.isPresent()) {
						availability = avail.get();

						// if default price exist for sku exit
						if (source.isDefaultPrice()) {
							Optional<ProductPrice> defaultPrice = availability.getPrices().stream()
									.filter(p -> p.isDefaultPrice()).findAny();
							if (defaultPrice.isPresent()) {
								//throw new ConversionRuntimeException(
								//		"Default Price already exist for product with sku [" + source.getSku() + "]");
								destination = defaultPrice.get();
							}
						}
					}
				}

			}

			if (availability == null) {

				com.salesmanager.core.model.catalog.product.Product product = productService.getBySku(source.getSku(),
						store, language);
				if (product == null) {
					throw new ConversionRuntimeException("Product with sku [" + source.getSku()
							+ "] not found for MerchantStore [" + store.getCode() + "]");
				}

				availability = new ProductAvailability();
				availability.setProduct(product);
				availability.setRegion(Constants.ALL_REGIONS);
			}

			destination.setProductAvailability(availability);
			destination.setDefaultPrice(source.isDefaultPrice());
			destination.setProductPriceAmount(source.getPrice());
			destination.setCode(source.getCode());
			destination.setProductPriceSpecialAmount(source.getDiscountedPrice());
			if (source.getDiscountStartDate() != null) {
				Date startDate = DateUtil.getDate(source.getDiscountStartDate());

				destination.setProductPriceSpecialStartDate(startDate);
			}
			if (source.getDiscountEndDate() != null) {
				Date endDate = DateUtil.getDate(source.getDiscountEndDate());

				destination.setProductPriceSpecialEndDate(endDate);
			}
			availability.getPrices().add(destination);
			destination.setProductAvailability(availability);
			destination.setDescriptions(this.getProductPriceDescriptions(destination, source.getDescriptions(), store));

			
			destination.setDefaultPrice(source.isDefaultPrice());

		} catch (Exception e) {

			throw new ConversionRuntimeException(e);
		}
		return destination;
	}

	private Set<ProductPriceDescription> getProductPriceDescriptions(ProductPrice price,
			List<com.salesmanager.shop.model.catalog.product.ProductPriceDescription> descriptions,
			MerchantStore store) {
		if (CollectionUtils.isEmpty(descriptions)) {
			return Collections.emptySet();
		}
		Set<ProductPriceDescription> descs = new HashSet<ProductPriceDescription>();
		for (com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc : descriptions) {
			ProductPriceDescription description = null;
			if (CollectionUtils.isNotEmpty(price.getDescriptions())) {
				for (ProductPriceDescription d : price.getDescriptions()) {
					if (isPositive(desc.getId()) && desc.getId().equals(d.getId())) {
						desc.setId(d.getId());
					}
				}
			}
			description = getDescription(desc);
			description.setProductPrice(price);
			descs.add(description);
		}
		return descs;
	}

	private ProductPriceDescription getDescription(
			com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc) {
		ProductPriceDescription target = new ProductPriceDescription();
		target.setDescription(desc.getDescription());
		target.setName(desc.getName());
		target.setTitle(desc.getTitle());
		target.setId(null);
		if (isPositive(desc.getId())) {
			target.setId(desc.getId());
		}
		Language lang = getLanguage(desc);
		target.setLanguage(lang);
		return target;

	}

	private Language getLanguage(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc) {
		try {
			return Optional.ofNullable(languageService.getByCode(desc.getLanguage()))
					.orElseThrow(() -> new ConversionRuntimeException(
							"Language is null for code " + desc.getLanguage() + " use language ISO code [en, fr ...]"));
		} catch (ServiceException e) {
			throw new ConversionRuntimeException(e);
		}
	}

}
