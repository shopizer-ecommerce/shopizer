package com.salesmanager.core.business.repositories.customer;

import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.merchant.MerchantStore;



public interface CustomerRepositoryCustom {

	CustomerList listByStore(MerchantStore store, CustomerCriteria criteria);
	

}
