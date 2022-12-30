package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.product.ProductEntity;
import com.salesmanager.shop.model.entity.ReadableDescription;

public class ReadableMinimalProduct extends ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ReadableDescription description;
	private ReadableProductPrice productPrice;
	private String finalPrice = "0";
	private String originalPrice = null;
	private ReadableImage image;
	private List<ReadableImage> images;
	
	
	public ReadableDescription getDescription() {
		return description;
	}
	public void setDescription(ReadableDescription description) {
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
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		this.images = images;
	}


}
