package com.salesmanager.shop.model.shop;

import java.util.ArrayList;
import java.util.List;

public class MerchantStoreBrand {
  
  private String logo;
  private List<MerchantConfigEntity> socialNetworks = new ArrayList<MerchantConfigEntity>();
  public String getLogo() {
    return logo;
  }
  public void setLogo(String logo) {
    this.logo = logo;
  }
  public List<MerchantConfigEntity> getSocialNetworks() {
    return socialNetworks;
  }
  public void setSocialNetworks(List<MerchantConfigEntity> socialNetworks) {
    this.socialNetworks = socialNetworks;
  }

}
