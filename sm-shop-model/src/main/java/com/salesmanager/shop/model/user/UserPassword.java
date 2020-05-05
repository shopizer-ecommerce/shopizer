package com.salesmanager.shop.model.user;

import java.io.Serializable;

/**
 * Object containing password information
 * for change password request
 * @author carlsamson
 *
 */
public class UserPassword implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  String password = null;
  String changePassword = null;
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getChangePassword() {
    return changePassword;
  }
  public void setChangePassword(String changePassword) {
    this.changePassword = changePassword;
  }

}
