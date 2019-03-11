package com.salesmanager.shop.model.user;

public class ReadableUser extends UserEntity {

  /**
   * 
   */
  private String lastAccess;
  private String loginTime;
  private String merchant;
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
  public String getMerchant() {
    return merchant;
  }
  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }


}
