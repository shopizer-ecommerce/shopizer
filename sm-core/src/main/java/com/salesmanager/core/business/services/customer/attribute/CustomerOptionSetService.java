package com.salesmanager.core.business.services.customer.attribute;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public interface CustomerOptionSetService extends SalesManagerEntityService<Long, CustomerOptionSet> {



	void saveOrUpdate(CustomerOptionSet entity) throws ServiceException;




	List<CustomerOptionSet> listByStore(MerchantStore store,
			Language language) throws ServiceException;




	List<CustomerOptionSet> listByOption(CustomerOption option,
			MerchantStore store) throws ServiceException;
	

	List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue,
			MerchantStore store) throws ServiceException;

}
