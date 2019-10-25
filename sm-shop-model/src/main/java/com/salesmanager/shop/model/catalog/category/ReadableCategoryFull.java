package com.salesmanager.shop.model.catalog.category;

import java.util.ArrayList;
import java.util.List;

public class ReadableCategoryFull extends ReadableCategory {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();

  public List<CategoryDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<CategoryDescription> descriptions) {
    this.descriptions = descriptions;
  }

}
