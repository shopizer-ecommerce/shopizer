package com.salesmanager.shop.model.order;

import java.io.Serializable;
import java.util.List;

public class ReadableOrderProduct extends OrderProductEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String price;
	private String subTotal;
	
	private List<ReadableOrderProductAttribute> attributes = null;
	
	private String sku;
	private String image;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public List<ReadableOrderProductAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<ReadableOrderProductAttribute> attributes) {
		this.attributes = attributes;
	}


}
