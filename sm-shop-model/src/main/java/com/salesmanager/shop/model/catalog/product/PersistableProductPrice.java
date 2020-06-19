package com.salesmanager.shop.model.catalog.product;

import java.util.ArrayList;
import java.util.List;

public class PersistableProductPrice extends ProductPriceEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<ProductPriceDescription> descriptions = new ArrayList<ProductPriceDescription>();

  public List<ProductPriceDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<ProductPriceDescription> descriptions) {
    this.descriptions = descriptions;
  }

}
