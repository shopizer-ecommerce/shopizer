package com.salesmanager.core.business.catalog.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.currency.model.Currency;


/**
 * Services for Product item price calculation. 
 * @author Carl Samson
 *
 */
public interface PricingService {

	/**
	 * Calculates the FinalPrice of a Product taking into account
	 * all defined prices and possible rebates
	 * @param product
	 * @return
	 * @throws ServiceException
	 */
	FinalPrice calculateProductPrice(Product product) throws ServiceException;

	/**
	 * Calculates the FinalPrice of a Product taking into account
	 * all defined prices and possible rebates. It also applies other calculation
	 * based on the customer
	 * @param product
	 * @param customer
	 * @return
	 * @throws ServiceException
	 */
	FinalPrice calculateProductPrice(Product product, Customer customer)
			throws ServiceException;

	/**
	 * Calculates the FinalPrice of a Product taking into account
	 * all defined prices and possible rebates. This method should be used to calculate
	 * any additional prices based on the default attributes or based on the user selected attributes.
	 * @param product
	 * @param attributes
	 * @return
	 * @throws ServiceException
	 */
	FinalPrice calculateProductPrice(Product product,
			List<ProductAttribute> attributes) throws ServiceException;

	/**
	 * Calculates the FinalPrice of a Product taking into account
	 * all defined prices and possible rebates. This method should be used to calculate
	 * any additional prices based on the default attributes or based on the user selected attributes.
	 * It also applies other calculation based on the customer
	 * @param product
	 * @param attributes
	 * @param customer
	 * @return
	 * @throws ServiceException
	 */
	FinalPrice calculateProductPrice(Product product,
			List<ProductAttribute> attributes, Customer customer)
			throws ServiceException;

	/**
	 * Method to be used to print a displayable formated amount to the end user
	 * @param amount
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	String getDisplayAmount(BigDecimal amount, MerchantStore store)
			throws ServiceException;
	
	/**
	 * Method to be used when building an amount formatted with the appropriate currency
	 * @param amount
	 * @param locale
	 * @param currency
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency, MerchantStore store)
			throws ServiceException;
	
	/**
	 * String format of the money amount without currency symbol
	 * @param amount
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	String getStringAmount(BigDecimal amount, MerchantStore store)
			throws ServiceException;
}
