package com.salesmanager.shop.model.catalog.product.inventory;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableInventory extends InventoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;

	private ReadableMerchantStore store;
	private String sku;
	private List<ReadableProductPrice> prices = new ArrayList<ReadableProductPrice>();
	private String price;

	public ReadableMerchantStore getStore() {
		return store;
	}

	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}

	public List<ReadableProductPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReadableProductPrice> prices) {
		this.prices = prices;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


}
