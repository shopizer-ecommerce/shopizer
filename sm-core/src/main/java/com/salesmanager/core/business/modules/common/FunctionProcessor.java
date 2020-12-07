package com.salesmanager.core.business.modules.common;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface FunctionProcessor<K> {
	
	void process(String event, K entity, MerchantStore store);
	void process(String event, K entity, Customer customer, MerchantStore store);

}
 