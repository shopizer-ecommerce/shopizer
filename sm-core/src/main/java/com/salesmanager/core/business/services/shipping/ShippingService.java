package com.salesmanager.core.business.services.shipping;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.*;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;

import java.util.List;
import java.util.Map;


public interface ShippingService {

    /**
     * Returns a list of supported countries (ship to country list) configured by merchant
     * when the merchant configured shipping National and has saved a list of ship to country
     * from the list
     *
     */
     List<String> getSupportedCountries(MerchantStore store)
            throws ServiceException;

    void setSupportedCountries(MerchantStore store,
                                      List<String> countryCodes) throws ServiceException;

    /**
     * Returns a list of available shipping modules
     *
     */
     List<IntegrationModule> getShippingMethods(MerchantStore store)
            throws ServiceException;


    /**
     * Returns a list of configured shipping modules for a given merchant
     *
     */
    Map<String, IntegrationConfiguration> getShippingModulesConfigured(
            MerchantStore store) throws ServiceException;

    /**
     * Adds a Shipping configuration
     *
     */
    void saveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration,
                                              MerchantStore store) throws ServiceException;

    /**
     * ShippingType (NATIONAL, INTERNATIONSL)
     * ShippingBasisType (SHIPPING, BILLING)
     * ShippingPriceOptionType (ALL, LEAST, HIGHEST)
     * Packages
     * Handling
     *
     */
    ShippingConfiguration getShippingConfiguration(MerchantStore store)
            throws ServiceException;

    /**
     * Saves ShippingConfiguration for a given MerchantStore
     *
     */
    void saveShippingConfiguration(ShippingConfiguration shippingConfiguration,
                                   MerchantStore store) throws ServiceException;

    void removeShippingQuoteModuleConfiguration(String moduleCode,
                                                MerchantStore store) throws ServiceException;

    /**
     * Provides detailed information on boxes that will be used
     * when getting a ShippingQuote
     *
     */
    List<PackageDetails> getPackagesDetails(List<ShippingProduct> products,
                                            MerchantStore store) throws ServiceException;

    /**
     * Get a list of ShippingQuote from a configured
     * shipping gateway. The quotes are displayed to the end user so he can pick
     * the ShippingOption he wants
     *
     */
    ShippingQuote getShippingQuote(Long shoppingCartId, MerchantStore store, Delivery delivery,
                                   List<ShippingProduct> products, Language language)
            throws ServiceException;


    /**
     * Returns a shipping module configuration given a moduleCode
     *
     */
    IntegrationConfiguration getShippingConfiguration(String moduleCode,
                                                      MerchantStore store) throws ServiceException;

    /**
     * Retrieves the custom configuration for a given module
     *
     */


    CustomIntegrationConfiguration getCustomShippingConfiguration(
            String moduleCode, MerchantStore store) throws ServiceException;

    /**
     * Weight based configuration
     *
     */
    void saveCustomShippingConfiguration(String moduleCode,
                                         CustomIntegrationConfiguration shippingConfiguration,
                                         MerchantStore store) throws ServiceException;

    /**
     * Removes a custom shipping quote
     * module
     *
     */
    void removeCustomShippingQuoteModuleConfiguration(String moduleCode,
                                                      MerchantStore store) throws ServiceException;

    /**
     * The {@link ShippingSummary} is built from the ShippingOption the user has selected
     * The ShippingSummary is used for order calculation
     *
     */
    ShippingSummary getShippingSummary(MerchantStore store, ShippingQuote shippingQuote,
                                       ShippingOption selectedShippingOption) throws ServiceException;

    /**
     * Returns a list of supported countries (ship to country list) configured by merchant
     * If the merchant configured shipping National, then only store country will be in the list
     * If the merchant configured shipping International, then the list of accepted country is returned
     * from the list
     *
     */
    List<Country> getShipToCountryList(MerchantStore store, Language language)
            throws ServiceException;

    /**
     * Determines if Shipping should be proposed to the customer
     *
     */
    boolean requiresShipping(List<ShoppingCartItem> items, MerchantStore store) throws ServiceException;

    /**
     * Returns shipping metadata and how shipping is configured for a given store
     *
     */
    ShippingMetaData getShippingMetaData(MerchantStore store) throws ServiceException;

    /**
     * Based on merchant configurations will return if tax must be calculated on shipping
     *
     * @param store
     * @return
     * @throws ServiceException
     */
    boolean hasTaxOnShipping(MerchantStore store) throws ServiceException;


}