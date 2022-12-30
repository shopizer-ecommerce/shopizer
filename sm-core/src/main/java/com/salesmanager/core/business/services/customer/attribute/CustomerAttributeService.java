package com.salesmanager.core.business.services.customer.attribute;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.merchant.MerchantStore;



public interface CustomerAttributeService extends
		SalesManagerEntityService<Long, CustomerAttribute> {

	void saveOrUpdate(CustomerAttribute customerAttribute)
			throws ServiceException;

	CustomerAttribute getByCustomerOptionId(MerchantStore store,
			Long customerId, Long id);

	List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store,
			Long id);

	List<CustomerAttribute> getByOptionId(MerchantStore store, Long id);


	List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer);
	

}
