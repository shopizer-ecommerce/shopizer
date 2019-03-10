package com.salesmanager.shop.model.user;

public class PersistableUser extends UserEntity {

  private String password;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
