package com.salesmanager.core.business.services.customer.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

import java.util.List;


public interface CustomerOptionValueService extends SalesManagerEntityService<Long, CustomerOptionValue> {


    List<CustomerOptionValue> listByStore(MerchantStore store, Language language)
            throws ServiceException;

    void saveOrUpdate(CustomerOptionValue entity) throws ServiceException;

    CustomerOptionValue getByCode(MerchantStore store, String optionValueCode);


}
