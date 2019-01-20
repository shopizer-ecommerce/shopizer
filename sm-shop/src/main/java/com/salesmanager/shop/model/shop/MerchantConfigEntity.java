package com.salesmanager.shop.model.shop;

import com.salesmanager.core.model.system.MerchantConfigurationType;
import com.salesmanager.shop.model.entity.Entity;

public class MerchantConfigEntity extends Entity {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String key;
  private MerchantConfigurationType type;
  private String value;
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public MerchantConfigurationType getType() {
    return type;
  }
  public void setType(MerchantConfigurationType type) {
    this.type = type;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

}
