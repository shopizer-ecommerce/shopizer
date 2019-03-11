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

}
