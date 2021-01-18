package com.salesmanager.shop.store.controller.customer.facade.v1;

import java.security.Principal;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface CustomerFacade {
	
	/** validates username with principal **/
	void authorize(Customer customer,  Principal principal);
	
	/**
	 * 
	 * Forgot password functionality
	 * @param customerName
	 * @param store
	 * @param language
	 */
	void requestPasswordReset(String customerName, String customerContextPath, MerchantStore store, Language language);
	
	/**
	 * Validates if a password request is valid
	 * @param token
	 * @param store
	 */
	void validatePasswordRequestToken(String token, String store);

}
