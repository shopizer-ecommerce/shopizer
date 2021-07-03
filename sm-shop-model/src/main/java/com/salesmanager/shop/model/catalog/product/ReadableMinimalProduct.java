package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;

public class ReadableMinimalProduct extends ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductDescription description;
	private ReadableProductPrice productPrice;
	private String finalPrice = "0";
	private String originalPrice = null;
	private ReadableImage image;
	public ProductDescription getDescription() {
		return description;
	}
	public void setDescription(ProductDescription description) {
		this.description = description;
	}
	public ReadableProductPrice getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(ReadableProductPrice productPrice) {
		this.productPrice = productPrice;
	}
	public String getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public ReadableImage getImage() {
		return image;
	}
	public void setImage(ReadableImage image) {
		this.image = image;
	}


}
