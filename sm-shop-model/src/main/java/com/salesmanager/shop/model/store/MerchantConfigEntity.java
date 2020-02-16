package com.salesmanager.shop.model.store;

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
  private boolean active;
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
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }

}
