package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableCatalog extends CatalogEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;
	private ReadableMerchantStore store;
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public ReadableMerchantStore getStore() {
		return store;
	}
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}


}
