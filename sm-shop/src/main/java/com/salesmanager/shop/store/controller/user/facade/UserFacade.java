package com.salesmanager.shop.store.controller.user.facade;

import java.util.List;

import com.salesmanager.core.model.reference.language.Language;
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
	ReadableUser findByUserName(String userName, Language lang) ;

  ReadableUser findByUserNameWithPermissions(String userName, Language lang);
	
	/**
	 * List permissions by group
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<ReadablePermission> findPermissionsByGroups(List<Integer> ids);
	
	/**
	 * Determines if a user is authorized to perform an action on a specific store
	 * @param userName
	 * @param merchantStoreCode
	 * @return
	 * @throws Exception
	 */
	boolean authorizedStore(String userName, String merchantStoreCode);
	

}
