package com.salesmanager.core.business.tax.service;

import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;

public interface TaxClassService extends SalesManagerEntityService<Long, TaxClass> {

	public List<TaxClass> listByStore(MerchantStore store) throws ServiceException;

	TaxClass getByCode(String code) throws ServiceException;

	TaxClass getByCode(String code, MerchantStore store)
			throws ServiceException;

}
