package com.salesmanager.shop.model.catalog.product.inventory;

import java.util.List;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;

public class PersistableInventory extends InventoryEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String store;
  private Long productId;
  private List<PersistableProductPrice> prices;
  public String getStore() {
    return store;
  }
  public void setStore(String store) {
    this.store = store;
  }
  public List<PersistableProductPrice> getPrices() {
    return prices;
  }
  public void setPrices(List<PersistableProductPrice> prices) {
    this.prices = prices;
  }
  public Long getProductId() {
    return productId;
  }
  public void setProductId(Long productId) {
    this.productId = productId;
  }

}
