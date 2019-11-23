package com.salesmanager.shop.model.marketplace;

import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableMarketPlace extends MarketPlaceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ReadableMerchantStore store;

	public ReadableMerchantStore getStore() {
		return store;
	}

	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}

}
