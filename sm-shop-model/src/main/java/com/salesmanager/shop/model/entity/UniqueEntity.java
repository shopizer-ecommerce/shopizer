package com.salesmanager.shop.model.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class UniqueEntity implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @NotNull
  private String unique;
  @NotNull
  private String merchant;

  public String getUnique() {
    return unique;
  }

  public void setUnique(String unique) {
    this.unique = unique;
  }

  public String getMerchant() {
    return merchant;
  }

  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }

}
