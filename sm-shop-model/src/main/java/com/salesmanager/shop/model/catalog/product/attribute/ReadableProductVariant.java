package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableProductVariant implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  //option name
  private String name;
  private List<ReadableProductVariantValue> options = new ArrayList<ReadableProductVariantValue>();

  public List<ReadableProductVariantValue> getOptions() {
    return options;
  }

  public void setOptions(List<ReadableProductVariantValue> options) {
    this.options = options;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



}
