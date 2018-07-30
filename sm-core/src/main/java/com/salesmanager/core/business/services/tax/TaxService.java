package com.salesmanager.core.business.services.tax;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.TaxConfiguration;
import com.salesmanager.core.model.tax.TaxItem;

import java.util.List;


public interface TaxService {

    /**
     * Retrieves tax configurations (TaxConfiguration) for a given MerchantStore
     */
    TaxConfiguration getTaxConfiguration(MerchantStore store)
            throws ServiceException;

    /**
     * Saves ShippingConfiguration to MerchantConfiguration table
     */
    void saveTaxConfiguration(TaxConfiguration shippingConfiguration,
                              MerchantStore store) throws ServiceException;

    /**
     * Calculates tax over an OrderSummary
     */
    List<TaxItem> calculateTax(OrderSummary orderSummary, Customer customer,
                               MerchantStore store, Language language) throws ServiceException;


}
