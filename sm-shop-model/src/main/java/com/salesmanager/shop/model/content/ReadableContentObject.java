package com.salesmanager.shop.model.content;

public class ReadableContentObject extends ObjectContent {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private boolean isDisplayedInMenu;
  private String code;
  private Long id;
  
  public boolean isDisplayedInMenu() {
    return isDisplayedInMenu;
  }
  public void setDisplayedInMenu(boolean isDisplayedInMenu) {
    this.isDisplayedInMenu = isDisplayedInMenu;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

}
