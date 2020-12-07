package com.salesmanager.core.business.services.shipping;

import java.util.List;
import java.util.Map;


import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingMetaData;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;



public interface ShippingService {

	/**
	 * Returns a list of supported countries (ship to country list) configured by merchant
	 * when the merchant configured shipping National and has saved a list of ship to country
	 * from the list
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	List<String> getSupportedCountries(MerchantStore store)
			throws ServiceException;

	void setSupportedCountries(MerchantStore store,
			List<String> countryCodes) throws ServiceException;

	/**
	 * Returns a list of available shipping modules
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	List<IntegrationModule> getShippingMethods(MerchantStore store)
			throws ServiceException;

	
	/**
	 * Returns a list of configured shipping modules for a given merchant
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	Map<String, IntegrationConfiguration> getShippingModulesConfigured(
			MerchantStore store) throws ServiceException;

	/**
	 * Adds a Shipping configuration
	 * @param configuration
	 * @param store
	 * @throws ServiceException
	 */
	void saveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration,
			MerchantStore store) throws ServiceException;

	/**
	 * ShippingType (NATIONAL, INTERNATIONSL)
	 * ShippingBasisType (SHIPPING, BILLING)
	 * ShippingPriceOptionType (ALL, LEAST, HIGHEST)
	 * Packages
	 * Handling
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	ShippingConfiguration getShippingConfiguration(MerchantStore store)
			throws ServiceException;

	/**
	 * Saves ShippingConfiguration for a given MerchantStore
	 * @param shippingConfiguration
	 * @param store
	 * @throws ServiceException
	 */
	void saveShippingConfiguration(ShippingConfiguration shippingConfiguration,
			MerchantStore store) throws ServiceException;

	void removeShippingQuoteModuleConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	/**
	 * Provides detailed information on boxes that will be used
	 * when getting a ShippingQuote
	 * @param products
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	List<PackageDetails> getPackagesDetails(List<ShippingProduct> products,
			MerchantStore store) throws ServiceException;

	/**
	 * Get a list of ShippingQuote from a configured
	 * shipping gateway. The quotes are displayed to the end user so he can pick
	 * the ShippingOption he wants
	 * @param store
	 * @param shoppingCartId
	 * @param customer
	 * @param products
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	ShippingQuote getShippingQuote(Long shoppingCartId, MerchantStore store, Delivery delivery,
			List<ShippingProduct> products, Language language)
			throws ServiceException;
	

	/**
	 * Returns a shipping module configuration given a moduleCode
	 * @param moduleCode
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	IntegrationConfiguration getShippingConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;
	
	/**
	 * Retrieves the custom configuration for a given module
	 * @param moduleCode
	 * @param store
	 * @return
	 * @throws ServiceException
	 */


	CustomIntegrationConfiguration getCustomShippingConfiguration(
			String moduleCode, MerchantStore store) throws ServiceException;

	/**
	 * Weight based configuration
	 * @param moduleCode
	 * @param shippingConfiguration
	 * @param store
	 * @throws ServiceException
	 */
	void saveCustomShippingConfiguration(String moduleCode,
			CustomIntegrationConfiguration shippingConfiguration,
			MerchantStore store) throws ServiceException;

	/**
	 * Removes a custom shipping quote
	 * module
	 * @param moduleCode
	 * @param store
	 * @throws ServiceException
	 */
	void removeCustomShippingQuoteModuleConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	/**
	 * The {@link ShippingSummary} is built from the ShippingOption the user has selected
	 * The ShippingSummary is used for order calculation
	 * @param store
	 * @param shippingQuote
	 * @param selectedShippingOption
	 * @return
	 * @throws ServiceException
	 */
	ShippingSummary getShippingSummary(MerchantStore store, ShippingQuote shippingQuote, 
			ShippingOption selectedShippingOption) throws ServiceException;

	/**
	 * Returns a list of supported countries (ship to country list) configured by merchant
	 * If the merchant configured shipping National, then only store country will be in the list
	 * If the merchant configured shipping International, then the list of accepted country is returned
	 * from the list
	 * @param store
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<Country> getShipToCountryList(MerchantStore store, Language language)
			throws ServiceException;
	
	/**
	 * Determines if Shipping should be proposed to the customer
	 * @param items
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	boolean requiresShipping(List<ShoppingCartItem> items, MerchantStore store) throws ServiceException;

	/**
	 * Returns shipping metadata and how shipping is configured for a given store
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	ShippingMetaData getShippingMetaData(MerchantStore store) throws ServiceException;
	
	/**
	 * Based on merchant configurations will return if tax must be calculated on shipping
	 * @param store
	 * @return
	 * @throws ServiceException
	 */
	boolean hasTaxOnShipping(MerchantStore store) throws ServiceException;


}