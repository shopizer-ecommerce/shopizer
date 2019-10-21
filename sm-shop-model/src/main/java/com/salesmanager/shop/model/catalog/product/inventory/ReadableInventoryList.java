package com.salesmanager.shop.model.catalog.product.inventory;

import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableInventoryList extends ReadableList {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<ReadableInventory> inventory = new ArrayList<ReadableInventory>();
  public List<ReadableInventory> getInventory() {
    return inventory;
  }
  public void setInventory(List<ReadableInventory> inventory) {
    this.inventory = inventory;
  }

}
