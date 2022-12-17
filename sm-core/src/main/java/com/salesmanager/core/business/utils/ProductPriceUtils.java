package com.salesmanager.core.business.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;

/**
 * This class determines the price that is displayed in the catalogue for a
 * given item. It does not calculate the total price for a given item
 * 
 * @author casams1
 *
 */
@Component("priceUtil")
public class ProductPriceUtils {

	private final static char DECIMALCOUNT = '2';
	private final static char DECIMALPOINT = '.';
	private final static char THOUSANDPOINT = ',';

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceUtils.class);

	/**
	 * Get the price without discount
	 * 
	 * @param store
	 * @param product
	 * @param locale
	 * @return
	 */
	// Pricer
	public BigDecimal getPrice(MerchantStore store, Product product, Locale locale) {

		BigDecimal defaultPrice = new BigDecimal(0);

		Set<ProductAvailability> availabilities = product.getAvailabilities();
		for (ProductAvailability availability : availabilities) {

			Set<ProductPrice> prices = availability.getPrices();
			for (ProductPrice price : prices) {

				if (price.isDefaultPrice()) {
					defaultPrice = price.getProductPriceAmount();
				}
			}
		}

		return defaultPrice;
	}

	/**
	 * This method calculates the final price taking into account all attributes
	 * included having a specified default attribute with an attribute price gt 0 in
	 * the product object. The calculation is based on the default price. Attributes
	 * may be null
	 * 
	 * @param Product
	 * @param List<ProductAttribute>
	 * @return FinalPrice
	 */
	// Pricer
	public FinalPrice getFinalPrice(Product product, List<ProductAttribute> attributes) throws ServiceException {

		FinalPrice finalPrice = calculateFinalPrice(product);

		// attributes
		BigDecimal attributePrice = null;
		if (attributes != null && attributes.size() > 0) {
			for (ProductAttribute attribute : attributes) {
				if (attribute.getProductAttributePrice() != null
						&& attribute.getProductAttributePrice().doubleValue() > 0) {
					if (attributePrice == null) {
						attributePrice = new BigDecimal(0);
					}
					attributePrice = attributePrice.add(attribute.getProductAttributePrice());
				}
			}

			if (attributePrice != null && attributePrice.doubleValue() > 0) {
				BigDecimal fp = finalPrice.getFinalPrice();
				fp = fp.add(attributePrice);
				finalPrice.setFinalPrice(fp);

				BigDecimal op = finalPrice.getOriginalPrice();
				op = op.add(attributePrice);
				finalPrice.setOriginalPrice(op);

				BigDecimal dp = finalPrice.getDiscountedPrice();
				if (dp != null) {
					dp = dp.add(attributePrice);
					finalPrice.setDiscountedPrice(dp);
				}

			}
		}

		return finalPrice;
	}

	/**
	 * This is the final price calculated from all configured prices and all
	 * possibles discounts. This price does not calculate the attributes or other
	 * prices than the default one
	 * 
	 * @param store
	 * @param product
	 * @param locale
	 * @return
	 */
	// Pricer
	public FinalPrice getFinalPrice(Product product) throws ServiceException {

		FinalPrice finalPrice = calculateFinalPrice(product);

		// attributes
		BigDecimal attributePrice = null;
		if (product.getAttributes() != null && product.getAttributes().size() > 0) {
			for (ProductAttribute attribute : product.getAttributes()) {
				if (attribute.getAttributeDefault()) {
					if (attribute.getProductAttributePrice() != null
							&& attribute.getProductAttributePrice().doubleValue() > 0) {
						if (attributePrice == null) {
							attributePrice = new BigDecimal(0);
						}
						attributePrice = attributePrice.add(attribute.getProductAttributePrice());
					}
				}
			}

			if (attributePrice != null && attributePrice.doubleValue() > 0) {
				BigDecimal fp = finalPrice.getFinalPrice();
				fp = fp.add(attributePrice);
				finalPrice.setFinalPrice(fp);

				BigDecimal op = finalPrice.getOriginalPrice();
				op = op.add(attributePrice);
				finalPrice.setOriginalPrice(op);
			}
		}

		finalPrice.setStringPrice(getStringAmount(finalPrice.getFinalPrice()));
		if (finalPrice.isDiscounted()) {
			finalPrice.setStringDiscountedPrice(getStringAmount(finalPrice.getDiscountedPrice()));
		}
		return finalPrice;

	}

	// Pricer
	public FinalPrice getFinalPrice(ProductVariant variant) throws ServiceException {

		Validate.notNull(variant, "ProductVariant must not be null");
		Validate.notNull(variant.getProduct(), "variant.product must not be null");
		Validate.notNull(variant.getAuditSection(), "variant.availabilities must not be null or empty");

		FinalPrice finalPrice = null;
		List<FinalPrice> otherPrices = null;

		for (ProductAvailability availability : variant.getAvailabilities()) {
			if (!StringUtils.isEmpty(availability.getRegion())
					&& availability.getRegion().equals(Constants.ALL_REGIONS)) {// TODO REL 2.1 accept a region
				Set<ProductPrice> prices = availability.getPrices();
				for (ProductPrice price : prices) {

					FinalPrice p = finalPrice(price);
					if (price.isDefaultPrice()) {
						finalPrice = p;
					} else {
						if (otherPrices == null) {
							otherPrices = new ArrayList<FinalPrice>();
						}
						otherPrices.add(p);
					}
				}
			}
		}

		if (finalPrice != null) {
			finalPrice.setAdditionalPrices(otherPrices);
		} else {
			if (otherPrices != null) {
				finalPrice = otherPrices.get(0);
			}
		}

		if (finalPrice == null) {
			throw new ServiceException(ServiceException.EXCEPTION_ERROR,
					"No inventory available to calculate the price. Availability should contain at least a region set to *");
		}

		return finalPrice;

	}

	public FinalPrice getFinalPrice(ProductAvailability availability) throws ServiceException {

		FinalPrice finalPrice = calculateFinalPrice(availability);

		if (finalPrice == null) {
			throw new ServiceException(ServiceException.EXCEPTION_ERROR,
					"No inventory available to calculate the price. Availability should contain at least a region set to *");
		}

		finalPrice.setStringPrice(getStringAmount(finalPrice.getFinalPrice()));
		if (finalPrice.isDiscounted()) {
			finalPrice.setStringDiscountedPrice(getStringAmount(finalPrice.getDiscountedPrice()));
		}
		return finalPrice;

	}

	/**
	 * This is the format that will be displayed in the admin input text fields when
	 * editing an entity having a BigDecimal to be displayed as a raw amount
	 * 1,299.99 The admin user will also be force to input the amount using that
	 * format
	 * 
	 * @param store
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String getAdminFormatedAmount(MerchantStore store, BigDecimal amount) throws Exception {

		if (amount == null) {
			return "";
		}

		NumberFormat nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);

		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));

		return nf.format(amount);
	}

	// Utility
	public String getStringAmount(BigDecimal amount) {

		if (amount == null) {
			return "";
		}

		NumberFormat nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);

		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));

		return nf.format(amount);
	}

	/**
	 * This method has to be used to format store front amounts It will display
	 * national format amount ex: $1,345.99 Rs.1.345.99 or international format
	 * USD1,345.79 INR1,345.79
	 * 
	 * @param store
	 * @param amount
	 * @return String
	 * @throws Exception
	 */
	// Utility
	public String getStoreFormatedAmountWithCurrency(MerchantStore store, BigDecimal amount) throws Exception {
		if (amount == null) {
			return "";
		}

		Currency currency = Constants.DEFAULT_CURRENCY;
		Locale locale = Constants.DEFAULT_LOCALE;

		try {
			currency = store.getCurrency().getCurrency();
			locale = new Locale(store.getDefaultLanguage().getCode(), store.getCountry().getIsoCode());
		} catch (Exception e) {
			LOGGER.error("Cannot create currency or locale instance for store " + store.getCode());
		}

		NumberFormat currencyInstance = null;

		if (store.isCurrencyFormatNational()) {
			currencyInstance = NumberFormat.getCurrencyInstance(locale);// national
		} else {
			currencyInstance = NumberFormat.getCurrencyInstance();// international
		}
		currencyInstance.setCurrency(currency);

		return currencyInstance.format(amount.doubleValue());

	}

	// Utility
	public String getFormatedAmountWithCurrency(Locale locale,
			com.salesmanager.core.model.reference.currency.Currency currency, BigDecimal amount) throws Exception {
		if (amount == null) {
			return "";
		}

		Currency curr = currency.getCurrency();
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
		currencyInstance.setCurrency(curr);
		return currencyInstance.format(amount.doubleValue());

	}

	/**
	 * This method will return the required formated amount with the appropriate
	 * currency
	 * 
	 * @param store
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String getAdminFormatedAmountWithCurrency(MerchantStore store, BigDecimal amount) throws Exception {
		if (amount == null) {
			return "";
		}

		NumberFormat nf = null;

		Currency currency = store.getCurrency().getCurrency();
		nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setCurrency(currency);

		return nf.format(amount);
	}

	/**
	 * Returns a formatted amount using Shopizer Currency requires internal
	 * java.util.Currency populated
	 * 
	 * @param currency
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	// Utility
	public String getFormatedAmountWithCurrency(com.salesmanager.core.model.reference.currency.Currency currency,
			BigDecimal amount) throws Exception {
		if (amount == null) {
			return "";
		}

		Validate.notNull(currency.getCurrency(), "Currency must be populated with java.util.Currency");
		NumberFormat nf = null;

		Currency curr = currency.getCurrency();
		nf = NumberFormat.getInstance(Constants.DEFAULT_LOCALE);
		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setCurrency(curr);

		return nf.format(amount);
	}

	/**
	 * This amount will be displayed to the end user
	 * 
	 * @param store
	 * @param amount
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	// Utility
	public String getFormatedAmountWithCurrency(MerchantStore store, BigDecimal amount, Locale locale)
			throws Exception {

		NumberFormat nf = null;

		Currency currency = store.getCurrency().getCurrency();

		nf = NumberFormat.getInstance(locale);
		nf.setCurrency(currency);
		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(DECIMALCOUNT)));

		return nf.format(amount);

	}

	/**
	 * Transformation of an amount of money submited by the admin user to be
	 * inserted as a BigDecimal in the database
	 * 
	 * @param amount
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	// Utility
	public BigDecimal getAmount(String amount) throws Exception {

		// validations
		/**
		 * 1) remove decimal and thousand
		 * 
		 * String.replaceAll(decimalPoint, ""); String.replaceAll(thousandPoint, "");
		 * 
		 * Should be able to parse to Integer
		 */
		StringBuilder newAmount = new StringBuilder();
		for (int i = 0; i < amount.length(); i++) {
			if (amount.charAt(i) != DECIMALPOINT && amount.charAt(i) != THOUSANDPOINT) {
				newAmount.append(amount.charAt(i));
			}
		}

		try {
			Integer.parseInt(newAmount.toString());
		} catch (Exception e) {
			throw new Exception("Cannot parse " + amount);
		}

		if (!amount.contains(Character.toString(DECIMALPOINT)) && !amount.contains(Character.toString(THOUSANDPOINT))
				&& !amount.contains(" ")) {

			if (matchPositiveInteger(amount)) {
				BigDecimalValidator validator = CurrencyValidator.getInstance();
				BigDecimal bdamount = validator.validate(amount, Locale.US);
				if (bdamount == null) {
					throw new Exception("Cannot parse " + amount);
				} else {
					return bdamount;
				}
			} else {
				throw new Exception("Not a positive integer " + amount);
			}

		} else {
			// TODO should not go this path in this current release
			StringBuilder pat = new StringBuilder();

			if (!StringUtils.isBlank(Character.toString(THOUSANDPOINT))) {
				pat.append("\\d{1,3}(" + THOUSANDPOINT + "?\\d{3})*");
			}

			pat.append("(\\" + DECIMALPOINT + "\\d{1," + DECIMALCOUNT + "})");

			Pattern pattern = Pattern.compile(pat.toString());
			Matcher matcher = pattern.matcher(amount);

			if (matcher.matches()) {

				Locale locale = Constants.DEFAULT_LOCALE;
				// TODO validate amount using old test case
				if (DECIMALPOINT == ',') {
					locale = Locale.GERMAN;
				}

				BigDecimalValidator validator = CurrencyValidator.getInstance();

				return validator.validate(amount, locale);
			} else {
				throw new Exception("Cannot parse " + amount);
			}
		}

	}

	// Move to OrderTotalService
	public BigDecimal getOrderProductTotalPrice(MerchantStore store, OrderProduct orderProduct) {

		BigDecimal finalPrice = orderProduct.getOneTimeCharge();
		finalPrice = finalPrice.multiply(new BigDecimal(orderProduct.getProductQuantity()));
		return finalPrice;
	}

	/**
	 * Determines if a ProductPrice has a discount
	 * 
	 * @param productPrice
	 * @return
	 */
	// discounter
	public boolean hasDiscount(ProductPrice productPrice) {

		Date today = new Date();

		// calculate discount price
		boolean hasDiscount = false;
		if (productPrice.getProductPriceSpecialStartDate() != null
				|| productPrice.getProductPriceSpecialEndDate() != null) {

			if (productPrice.getProductPriceSpecialStartDate() != null) {
				if (productPrice.getProductPriceSpecialStartDate().before(today)) {
					if (productPrice.getProductPriceSpecialEndDate() != null) {
						if (productPrice.getProductPriceSpecialEndDate().after(today)) {
							hasDiscount = true;
						}
					}
				}
			}
		}

		return hasDiscount;
	}

	private boolean matchPositiveInteger(String amount) {
		Pattern pattern = Pattern.compile("^[+]?\\d*$");
		Matcher matcher = pattern.matcher(amount);
		return matcher.matches();
	}

	private Set<ProductAvailability> applicableAvailabilities(Set<ProductAvailability> availabilities)
			throws ServiceException {
		if (CollectionUtils.isEmpty(availabilities)) {
			throw new ServiceException(ServiceException.EXCEPTION_ERROR,
					"No applicable inventory to calculate the price.");
		}

		return new HashSet<ProductAvailability>(availabilities.stream()
				.filter(a -> !CollectionUtils.isEmpty(a.getPrices())).collect(Collectors.toList()));
	}

	private FinalPrice calculateFinalPrice(Product product) throws ServiceException {

		FinalPrice finalPrice = null;
		List<FinalPrice> otherPrices = null;

		/**
		 * Since 3.2.0 The rule is
		 * 
		 * If product.variants contains exactly one variant If Variant has availability
		 * we use availability from variant Otherwise we use price
		 */

		Set<ProductAvailability> availabilities = null;
		if (!CollectionUtils.isEmpty(product.getVariants())) {
			Optional<ProductVariant> variants = product.getVariants().stream().filter(i -> i.isDefaultSelection())
					.findFirst();
			if (variants.isPresent()) {
				availabilities = variants.get().getAvailabilities();
				availabilities = this.applicableAvailabilities(availabilities);

			}
		}

		if (CollectionUtils.isEmpty(availabilities)) {
			availabilities = product.getAvailabilities();
			availabilities = this.applicableAvailabilities(availabilities);
		}

		for (ProductAvailability availability : availabilities) {
			if (!StringUtils.isEmpty(availability.getRegion())
					&& availability.getRegion().equals(Constants.ALL_REGIONS)) {// TODO REL 2.1 accept a region
				Set<ProductPrice> prices = availability.getPrices();
				for (ProductPrice price : prices) {

					FinalPrice p = finalPrice(price);
					if (price.isDefaultPrice()) {
						finalPrice = p;
					} else {
						if (otherPrices == null) {
							otherPrices = new ArrayList<FinalPrice>();
						}
						otherPrices.add(p);
					}
				}
			}
		}

		if (finalPrice != null) {
			finalPrice.setAdditionalPrices(otherPrices);
		} else {
			if (otherPrices != null) {
				finalPrice = otherPrices.get(0);
			}
		}

		if (finalPrice == null) {
			throw new ServiceException(ServiceException.EXCEPTION_ERROR,
					"No inventory available to calculate the price. Availability should contain at least a region set to *");
		}

		return finalPrice;

	}

	private FinalPrice calculateFinalPrice(ProductAvailability availability) throws ServiceException {

		FinalPrice finalPrice = null;
		List<FinalPrice> otherPrices = null;

		Set<ProductPrice> prices = availability.getPrices();
		for (ProductPrice price : prices) {

			FinalPrice p = finalPrice(price);
			if (price.isDefaultPrice()) {
				finalPrice = p;
			} else {
				if (otherPrices == null) {
					otherPrices = new ArrayList<FinalPrice>();
				}
				otherPrices.add(p);
			}

		}

		if (finalPrice != null) {
			finalPrice.setAdditionalPrices(otherPrices);
		} else {
			if (otherPrices != null) {
				finalPrice = otherPrices.get(0);
			}
		}

		if (finalPrice == null) {
			throw new ServiceException(ServiceException.EXCEPTION_ERROR,
					"No inventory available to calculate the price. Availability should contain at least a region set to *");
		}

		return finalPrice;

	}

	private FinalPrice finalPrice(ProductPrice price) {

		FinalPrice finalPrice = new FinalPrice();
		BigDecimal fPrice = price.getProductPriceAmount();
		BigDecimal oPrice = price.getProductPriceAmount();

		Date today = new Date();
		// calculate discount price
		boolean hasDiscount = false;
		if (price.getProductPriceSpecialStartDate() != null || price.getProductPriceSpecialEndDate() != null) {

			if (price.getProductPriceSpecialStartDate() != null) {
				if (price.getProductPriceSpecialStartDate().before(today)) {
					if (price.getProductPriceSpecialEndDate() != null) {
						if (price.getProductPriceSpecialEndDate().after(today)) {
							hasDiscount = true;
							fPrice = price.getProductPriceSpecialAmount();
							finalPrice.setDiscountEndDate(price.getProductPriceSpecialEndDate());
						}
					}

				}
			}

			if (!hasDiscount && price.getProductPriceSpecialStartDate() == null
					&& price.getProductPriceSpecialEndDate() != null) {
				if (price.getProductPriceSpecialEndDate().after(today)) {
					hasDiscount = true;
					fPrice = price.getProductPriceSpecialAmount();
					finalPrice.setDiscountEndDate(price.getProductPriceSpecialEndDate());
				}
			}
		} else {
			if (price.getProductPriceSpecialAmount() != null
					&& price.getProductPriceSpecialAmount().doubleValue() > 0) {
				hasDiscount = true;
				fPrice = price.getProductPriceSpecialAmount();
				finalPrice.setDiscountEndDate(price.getProductPriceSpecialEndDate());
			}
		}

		finalPrice.setProductPrice(price);
		finalPrice.setFinalPrice(fPrice);
		finalPrice.setOriginalPrice(oPrice);

		if (price.isDefaultPrice()) {
			finalPrice.setDefaultPrice(true);
		}
		if (hasDiscount) {
			discountPrice(finalPrice);
		}

		return finalPrice;
	}

	private void discountPrice(FinalPrice finalPrice) {

		finalPrice.setDiscounted(true);

		double arith = finalPrice.getProductPrice().getProductPriceSpecialAmount().doubleValue()
				/ finalPrice.getProductPrice().getProductPriceAmount().doubleValue();
		double fsdiscount = 100 - (arith * 100);
		Float percentagediscount = new Float(fsdiscount);
		int percent = percentagediscount.intValue();
		finalPrice.setDiscountPercent(percent);

		// calculate percent
		BigDecimal price = finalPrice.getOriginalPrice();
		finalPrice.setDiscountedPrice(finalPrice.getProductPrice().getProductPriceSpecialAmount());
	}

}