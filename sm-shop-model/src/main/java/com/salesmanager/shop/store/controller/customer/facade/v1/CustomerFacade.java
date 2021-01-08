package com.salesmanager.shop.store.controller.customer.facade.v1;

import java.security.Principal;

import com.salesmanager.core.model.customer.Customer;

public interface CustomerFacade {
	
	
	void authorize(Customer customer,  Principal principal);

}
