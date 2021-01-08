package com.salesmanager.shop.store.facade;

import java.security.Principal;

import org.jsoup.helper.Validate;
import org.springframework.stereotype.Service;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.customer.facade.v1.CustomerFacade;

@Service("customerFacadev1")
public class CustomerFacadeImpl implements CustomerFacade {

	@Override
	public void authorize(Customer customer, Principal principal) {
		
		Validate.notNull(customer, "Customer cannot be null");
		Validate.notNull(principal, "Principal cannot be null");
  
	    if( !principal.getName().equals(customer.getEmailAddress()) ) {
	      throw new UnauthorizedException("User [" + principal.getName() + "] unauthorized for customer [" + customer.getId() + "]");
	    }
		
	}

}
