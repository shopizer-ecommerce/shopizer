package com.salesmanager.shop.model.shop;

import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.store.MerchantStoreEntity;

public class PersistableMerchantStore extends MerchantStoreEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersistableAddress address;
	//code of parent store (can be null if retailer)
	private String retailerStore;

	public PersistableAddress getAddress() {
		return address;
	}

	public void setAddress(PersistableAddress address) {
		this.address = address;
	}

  public String getRetailerStore() {
    return retailerStore;
  }

  public void setRetailerStore(String retailerStore) {
    this.retailerStore = retailerStore;
  }

}
