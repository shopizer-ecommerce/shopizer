package com.salesmanager.shop.model.user;

public class ReadableUser extends UserEntity {

  /**
   * 
   */
  private String lastAccess;
  private String loginTime;
  private static final long serialVersionUID = 1L;
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


}
