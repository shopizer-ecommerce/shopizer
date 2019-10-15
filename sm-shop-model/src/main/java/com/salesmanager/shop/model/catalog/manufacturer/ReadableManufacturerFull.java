package com.salesmanager.shop.model.catalog.manufacturer;

import java.util.List;

public class ReadableManufacturerFull extends ReadableManufacturer {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<ManufacturerDescription> descriptions;

  public List<ManufacturerDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<ManufacturerDescription> descriptions) {
    this.descriptions = descriptions;
  }

}
