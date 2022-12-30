package com.salesmanager.shop.model.catalog.product.product.definition;

import com.salesmanager.shop.model.catalog.product.product.ProductSpecification;
import com.salesmanager.shop.model.entity.Entity;

/**
 * Applies to product version 2 management
 * @author carlsamson
 *
 */
public class ProductDefinition extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible = true;
	private boolean shipeable = true;
	private boolean virtual = false;
	private boolean canBePurchased = true;
	private String dateAvailable;
	private String identifier;
	private String sku; //to match v1 api
	private ProductSpecification productSpecifications;
	private int sortOrder;
	
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public ProductSpecification getProductSpecifications() {
		return productSpecifications;
	}
	public void setProductSpecifications(ProductSpecification productSpecifications) {
		this.productSpecifications = productSpecifications;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public boolean isShipeable() {
		return shipeable;
	}
	public void setShipeable(boolean shipeable) {
		this.shipeable = shipeable;
	}
	public boolean isVirtual() {
		return virtual;
	}
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	public boolean isCanBePurchased() {
		return canBePurchased;
	}
	public void setCanBePurchased(boolean canBePurchased) {
		this.canBePurchased = canBePurchased;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}


}
