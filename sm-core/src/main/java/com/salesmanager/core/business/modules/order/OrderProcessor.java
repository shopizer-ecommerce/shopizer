package com.salesmanager.core.business.modules.order;


import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;

public interface OrderProcessor {
	
	public void processOrder(Order order, Customer customer, MerchantStore store);

}
