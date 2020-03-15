package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

/**
 * Lightweight version of Persistable product
 * @author carlsamson
 *
 */
public class LightPersistableProduct implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String price;
  private boolean available;
  private int quantity;
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public boolean isAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }

}
