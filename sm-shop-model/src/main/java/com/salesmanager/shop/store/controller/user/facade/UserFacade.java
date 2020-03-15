package com.salesmanager.shop.store.controller.user.facade;

import java.util.List;

import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.ReadableUserList;
import com.salesmanager.shop.model.user.UserPassword;

/**
 * Access to all methods for managing users
 * 
 * @author carlsamson
 *
 */
public interface UserFacade {

  /**
   * Finds a User by userName
   * 
   * @return
   * @throws Exception
   */
  ReadableUser findByUserName(String userName, String storeCode, Language lang);
  
  /**
   * Find user by id
   * @param id
   * @param storeCode
   * @param lang
   * @return
   */
  ReadableUser findById(Long id, String storeCode, Language lang);

  /**
   * Creates a User
   * @param user
   * @param store
   */
  ReadableUser create(PersistableUser user, MerchantStore store);

  /**
   * List permissions by group
   * 
   * @param ids
   * @return
   * @throws Exception
   */
  List<ReadablePermission> findPermissionsByGroups(List<Integer> ids);

  /**
   * Determines if a user is authorized to perform an action on a specific store
   * 
   * @param userName
   * @param merchantStoreCode
   * @return
   * @throws Exception
   */
  boolean authorizedStore(String userName, String merchantStoreCode);
  
  /**
   * Determines if a user is in a specific group
   * @param userName
   * @param groupName
   */
  void authorizedGroup(String userName, List<String> groupNames);
  
  /**
   * Check if user is in specific list of roles
   * @param userName
   * @param groupNames
   * @return
   */
  boolean userInRoles(String userName, List<String> groupNames);
  
  /**
   * Retrieve authenticated user
   * @return
   */
  String authenticatedUser();
  
  /**
   * Get by criteria
   * @param criteria
   * @return
   */
  @Deprecated
  ReadableUserList getByCriteria(Language language,String draw,Criteria criteria);
  
  /**
   * List users
   * @param criteria
   * @param page
   * @param count
   * @param language
   * @return
   */
  ReadableUserList listByCriteria (Criteria criteria, int page, int count, Language language);
  
  /**
   * Delete user
   * @param id
   */
  void delete(Long id, String storeCode);
  
  /**
   * Update User
   * @param user
   */
  ReadableUser update(Long id, String authenticatedUser, String storeCode, PersistableUser user);
  
  /**
   * Change password request
   * @param userId
   * @param authenticatedUser
   * @param storeCode
   * @param changePassword
   */
  void changePassword(Long userId, String authenticatedUser, UserPassword changePassword);

  void authorizedGroups(String authenticatedUser, PersistableUser user);

}
