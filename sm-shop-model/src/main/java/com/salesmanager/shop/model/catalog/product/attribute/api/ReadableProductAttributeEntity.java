package com.salesmanager.shop.model.catalog.product.attribute.api;

public class ReadableProductAttributeEntity extends ProductAttributeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productAttributeWeight;
	private String productAttributePrice;
	private String productAttributeUnformattedPrice;
	
	private ReadableProductOptionEntity option;
	private ReadableProductOptionValue optionValue;
	public String getProductAttributeWeight() {
		return productAttributeWeight;
	}
	public void setProductAttributeWeight(String productAttributeWeight) {
		this.productAttributeWeight = productAttributeWeight;
	}
	public String getProductAttributePrice() {
		return productAttributePrice;
	}
	public void setProductAttributePrice(String productAttributePrice) {
		this.productAttributePrice = productAttributePrice;
	}
	public ReadableProductOptionEntity getOption() {
		return option;
	}
	public void setOption(ReadableProductOptionEntity option) {
		this.option = option;
	}
	public ReadableProductOptionValue getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(ReadableProductOptionValue optionValue) {
		this.optionValue = optionValue;
	}
	public String getProductAttributeUnformattedPrice() {
		return productAttributeUnformattedPrice;
	}
	public void setProductAttributeUnformattedPrice(String productAttributeUnformattedPrice) {
		this.productAttributeUnformattedPrice = productAttributeUnformattedPrice;
	}


}
