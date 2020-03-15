package com.salesmanager.shop.model.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.entity.Entity;

public class PersistableContent extends Entity implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String code;
  private boolean isDisplayedInMenu;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
  
  public List<ObjectContent> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<ObjectContent> descriptions) {
    this.descriptions = descriptions;
  }

  public boolean isDisplayedInMenu() {
    return isDisplayedInMenu;
  }

  public void setDisplayedInMenu(boolean isDisplayedInMenu) {
    this.isDisplayedInMenu = isDisplayedInMenu;
  }

  private List<ObjectContent> descriptions = new ArrayList<ObjectContent>();

}
