

package com.salesmanager.shop.model.catalog.product.attribute;

public class ReadableProductOptionEntity extends ProductOptionEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ProductOptionDescription description;
  public ProductOptionDescription getDescription() {
    return description;
  }
  public void setDescription(ProductOptionDescription description) {
    this.description = description;
  }

}
