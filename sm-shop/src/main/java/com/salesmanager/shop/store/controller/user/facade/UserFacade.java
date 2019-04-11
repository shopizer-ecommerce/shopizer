package com.salesmanager.shop.store.controller.user.facade;

import java.util.List;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.ReadableUserList;

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
  ReadableUser findByUserName(String userName, Language lang);

  /**
   * Creates a User
   * @param user
   * @param store
   */
  void create(PersistableUser user, MerchantStore store);

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
   * Retrieve authenticated user
   * @return
   */
  String authenticatedUser();
  
  /**
   * Get by criteria
   * @param criteria
   * @return
   */
  ReadableUserList getByCriteria(Language language,String draw,Criteria criteria);
  
  /**
   * Delete user
   * @param userName
   */
  void delete(String userName);
  
  /**
   * Update User
   * @param user
   */
  ReadableUser update(String authenticatedUser, String storeCode, PersistableUser user);


}
