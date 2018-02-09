package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductAttributeEntity extends ProductAttribute implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal productAttributePrice;
	private int sortOrder;
	private BigDecimal productAttributeWeight;
	private boolean attributeDefault=false;



	public void setProductAttributePrice(BigDecimal productAttributePrice) {
		this.productAttributePrice = productAttributePrice;
	}
	public BigDecimal getProductAttributePrice() {
		return productAttributePrice;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setProductAttributeWeight(BigDecimal productAttributeWeight) {
		this.productAttributeWeight = productAttributeWeight;
	}
	public BigDecimal getProductAttributeWeight() {
		return productAttributeWeight;
	}
	public void setAttributeDefault(boolean attributeDefault) {
		this.attributeDefault = attributeDefault;
	}
	public boolean isAttributeDefault() {
		return attributeDefault;
	}
}
