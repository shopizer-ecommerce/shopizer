package com.salesmanager.shop.model.order;

import com.salesmanager.shop.model.customer.PersistableCustomer;

public class PersistableAnonymousOrderApi extends PersistableOrderApi {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private PersistableCustomer customer;

  public PersistableCustomer getCustomer() {
    return customer;
  }

  public void setCustomer(PersistableCustomer customer) {
    this.customer = customer;
  }

}
