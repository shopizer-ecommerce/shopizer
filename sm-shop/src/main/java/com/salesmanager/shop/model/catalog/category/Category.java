package com.salesmanager.shop.model.catalog.category;

import com.salesmanager.shop.model.Entity;
import java.io.Serializable;


public class Category extends Entity implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
