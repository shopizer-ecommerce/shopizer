package com.salesmanager.shop.store.controller.order.facade.v1;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.order.v1.ReadableOrderConfirmation;

public interface OrderFacade {
	
	ReadableOrderConfirmation orderConfirmation(Order order, Customer customer, MerchantStore store, Language language);

}
