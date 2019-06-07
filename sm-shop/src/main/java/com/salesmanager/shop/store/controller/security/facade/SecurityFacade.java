package com.salesmanager.shop.store.controller.security.facade;

import java.util.List;
import com.salesmanager.shop.model.security.ReadablePermission;

public interface SecurityFacade {
  
  /**
   * Get permissions by group
   * @param groups
   * @return
   */
  List<ReadablePermission> getPermissions(List<String> groups);
  
  /**
   * Validates password format
   * @param password
   * @return
   */
  public boolean validateUserPassword(final String password);
  
  /**
   * Encode clear password
   * @param password
   * @return
   */
  public String encodePassword(final String password);

  /**
   * Validate if both passwords match
   * @param modelPassword (should be encrypted)
   * @param newPassword (should be clear)
   * @return
   */
  public boolean matchPassword(String modelPassword, String newPassword);
}
