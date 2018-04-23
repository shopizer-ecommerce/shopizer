package com.salesmanager.shop.store.controller.user.facade;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.user.ReadableUser;

/**
 * Access to all methods for managing users
 * @author carlsamson
 *
 */
public interface UserFacade {
	
	/**
	 * Finds a User by userName
	 * @return
	 * @throws Exception
	 */
	ReadableUser findByUserName(String userName, Language lang) throws Exception;
	
	

}
