package com.salesmanager.shop.model.catalog.product.product.variant;

import com.salesmanager.shop.model.catalog.product.Product;

public class ProductVariant extends Product {

	private static final long serialVersionUID = 1L;
	private String store;
	/** use product id or sku **/
	private Long productId;
	private String sku;
	/** **/
	private boolean available;
	private String dateAvailable;
	private int sortOrder;
	
	private boolean defaultSelection;
	
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public boolean isDefaultSelection() {
		return defaultSelection;
	}
	public void setDefaultSelection(boolean defaultSelection) {
		this.defaultSelection = defaultSelection;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	

}
