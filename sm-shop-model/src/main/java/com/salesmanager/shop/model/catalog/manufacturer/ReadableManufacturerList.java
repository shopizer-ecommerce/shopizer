package com.salesmanager.shop.model.catalog.manufacturer;

import java.util.ArrayList;
import java.util.List;
import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableManufacturerList extends ReadableList {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private List<ReadableManufacturer> manufacturers = new ArrayList<ReadableManufacturer>();

  public List<ReadableManufacturer> getManufacturers() {
    return manufacturers;
  }

  public void setManufacturers(List<ReadableManufacturer> manufacturers) {
    this.manufacturers = manufacturers;
  }

}
