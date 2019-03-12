package com.salesmanager.shop.model.user;

import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.security.ReadablePermission;

public class ReadableUser extends UserEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String lastAccess;
  private String loginTime;
  private String merchant;

  private List<ReadablePermission> permissions = new ArrayList<ReadablePermission>();
  
  
  public String getLastAccess() {
    return lastAccess;
  }
  public void setLastAccess(String lastAccess) {
    this.lastAccess = lastAccess;
  }
  public String getLoginTime() {
    return loginTime;
  }
  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }
  public String getMerchant() {
    return merchant;
  }
  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }
  public List<ReadablePermission> getPermissions() {
    return permissions;
  }
  public void setPermissions(List<ReadablePermission> permissions) {
    this.permissions = permissions;
  }


}
