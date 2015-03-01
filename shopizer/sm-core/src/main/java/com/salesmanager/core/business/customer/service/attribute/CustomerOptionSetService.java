package com.salesmanager.core.business.customer.service.attribute;

import java.util.List;

import com.salesmanager.core.business.customer.model.attribute.CustomerOption;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface CustomerOptionSetService extends SalesManagerEntityService<Long, CustomerOptionSet> {



	void saveOrUpdate(CustomerOptionSet entity) throws ServiceException;




	List<CustomerOptionSet> listByStore(MerchantStore store,
			Language language) throws ServiceException;




	List<CustomerOptionSet> listByOption(CustomerOption option,
			MerchantStore store) throws ServiceException;
	

	List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue,
			MerchantStore store) throws ServiceException;

}
