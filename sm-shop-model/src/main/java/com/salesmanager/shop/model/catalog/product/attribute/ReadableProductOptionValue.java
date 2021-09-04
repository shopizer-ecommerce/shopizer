package com.salesmanager.shop.model.catalog.product.attribute;

public class ReadableProductOptionValue extends ProductOptionValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String price;
	private String image;
	private String description;


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
