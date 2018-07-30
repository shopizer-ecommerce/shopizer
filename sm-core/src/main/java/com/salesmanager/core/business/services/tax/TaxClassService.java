package com.salesmanager.core.business.services.tax;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

import java.util.List;

public interface TaxClassService extends SalesManagerEntityService<Long, TaxClass> {

    List<TaxClass> listByStore(MerchantStore store) throws ServiceException;

    TaxClass getByCode(String code) throws ServiceException;

    TaxClass getByCode(String code, MerchantStore store)
            throws ServiceException;

}
