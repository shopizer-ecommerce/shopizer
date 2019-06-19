package com.salesmanager.shop.model.entity;

import java.io.Serializable;

public class UniqueEntity implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String unique;

  public String getUnique() {
    return unique;
  }

  public void setUnique(String unique) {
    this.unique = unique;
  }

}
