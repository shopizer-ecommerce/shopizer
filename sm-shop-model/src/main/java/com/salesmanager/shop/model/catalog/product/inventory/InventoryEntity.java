package com.salesmanager.shop.model.catalog.product.inventory;

import com.salesmanager.shop.model.entity.Entity;

public class InventoryEntity extends Entity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int quantity;
  private String region;
  private String regionVariant;
  private String owner;
  private String dateAvailable;
  private boolean available;
  private int productQuantityOrderMin = 0;
  private int productQuantityOrderMax = 0;
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getRegionVariant() {
    return regionVariant;
  }
  public void setRegionVariant(String regionVariant) {
    this.regionVariant = regionVariant;
  }
  public String getOwner() {
    return owner;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }
  public boolean isAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }
  public int getProductQuantityOrderMin() {
    return productQuantityOrderMin;
  }
  public void setProductQuantityOrderMin(int productQuantityOrderMin) {
    this.productQuantityOrderMin = productQuantityOrderMin;
  }
  public int getProductQuantityOrderMax() {
    return productQuantityOrderMax;
  }
  public void setProductQuantityOrderMax(int productQuantityOrderMax) {
    this.productQuantityOrderMax = productQuantityOrderMax;
  }
  public String getDateAvailable() {
    return dateAvailable;
  }
  public void setDateAvailable(String dateAvailable) {
    this.dateAvailable = dateAvailable;
  }
  

}
