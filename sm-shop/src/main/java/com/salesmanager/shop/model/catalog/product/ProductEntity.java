package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * A product entity is used by services API
 * to populate or retrieve a Product entity
 * @author Carl Samson
 *
 */
public class ProductEntity extends Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal price;
	private int quantity = 0;
	private String sku;
	private boolean productShipeable = false;
	private boolean preOrder = false;
	private boolean productVirtual = false;
	private int quantityOrderMaximum =-1;//default unlimited
	private int quantityOrderMinimum = 1;//default 1
	private boolean productIsFree;
	private boolean available;
	private boolean visible;
	private BigDecimal productLength;
	private BigDecimal productWidth;
	private BigDecimal productHeight;
	private BigDecimal productWeight;
	private Double rating = 0D;
	private int ratingCount;
	private int sortOrder;
	private String dateAvailable;
	private String refSku;

	
	
	
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public boolean isProductShipeable() {
		return productShipeable;
	}
	public void setProductShipeable(boolean productShipeable) {
		this.productShipeable = productShipeable;
	}
	public boolean isProductIsFree() {
		return productIsFree;
	}
	public void setProductIsFree(boolean productIsFree) {
		this.productIsFree = productIsFree;
	}
	public BigDecimal getProductLength() {
		return productLength;
	}
	public void setProductLength(BigDecimal productLength) {
		this.productLength = productLength;
	}
	public BigDecimal getProductWidth() {
		return productWidth;
	}
	public void setProductWidth(BigDecimal productWidth) {
		this.productWidth = productWidth;
	}
	public BigDecimal getProductHeight() {
		return productHeight;
	}
	public void setProductHeight(BigDecimal productHeight) {
		this.productHeight = productHeight;
	}
	public BigDecimal getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(BigDecimal productWeight) {
		this.productWeight = productWeight;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setQuantityOrderMaximum(int quantityOrderMaximum) {
		this.quantityOrderMaximum = quantityOrderMaximum;
	}
	public int getQuantityOrderMaximum() {
		return quantityOrderMaximum;
	}
	public void setProductVirtual(boolean productVirtual) {
		this.productVirtual = productVirtual;
	}
	public boolean isProductVirtual() {
		return productVirtual;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public int getQuantityOrderMinimum() {
		return quantityOrderMinimum;
	}
	public void setQuantityOrderMinimum(int quantityOrderMinimum) {
		this.quantityOrderMinimum = quantityOrderMinimum;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public boolean isPreOrder() {
		return preOrder;
	}
	public void setPreOrder(boolean preOrder) {
		this.preOrder = preOrder;
	}
	public String getRefSku() {
		return refSku;
	}
	public void setRefSku(String refSku) {
		this.refSku = refSku;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
