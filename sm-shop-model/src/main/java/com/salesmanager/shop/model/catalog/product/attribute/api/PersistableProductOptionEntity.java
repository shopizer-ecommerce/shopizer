package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionEntity;

public class PersistableProductOptionEntity extends ProductOptionEntity {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<ProductOptionDescription> descriptions = new ArrayList<ProductOptionDescription>();
  public List<ProductOptionDescription> getDescriptions() {
    return descriptions;
  }
  public void setDescriptions(List<ProductOptionDescription> descriptions) {
    this.descriptions = descriptions;
  }

}
