package com.salesmanager.shop.model.user;

public class UserEntity extends User {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String defaultLanguage;
  private String userName;
  private boolean active;




  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}


}
