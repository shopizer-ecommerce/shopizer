package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import com.salesmanager.shop.model.entity.Entity;

public class ReadableProductPrice extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originalPrice;
	private String finalPrice;
	private boolean defaultPrice = false;
	private boolean discounted = false;
	private ProductPriceDescription description;

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	public ProductPriceDescription getDescription() {
		return description;
	}

	public void setDescription(ProductPriceDescription description) {
		this.description = description;
	}

	public boolean isDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

}
