package com.salesmanager.shop.store.controller.user.facade;

import java.util.List;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.model.security.ReadablePermission;
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
	
	/**
	 * List permissions by group
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<ReadablePermission> findPermissionsByGroups(List<Integer> ids) throws Exception;
	
	

}
