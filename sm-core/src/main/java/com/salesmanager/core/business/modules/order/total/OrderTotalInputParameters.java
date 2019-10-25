package com.salesmanager.core.business.modules.order.total;

/**
 * Input itm to be added to drules engine
 * @author carlsamson
 *
 */
public class OrderTotalInputParameters {
	
	private Double discount;//output value set by engine
	private String totalCode;//output value set by engine
	
	//input parameters
	private long productId;
	private String itemManufacturerCode;
	private String itemCategoryCode;
	private String shippingMethod;
	
	//might add variation based on other objects such as Customer
	
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getTotalCode() {
		return totalCode;
	}
	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}
	public String getItemManufacturerCode() {
		return itemManufacturerCode;
	}
	public void setItemManufacturerCode(String itemManufacturerCode) {
		this.itemManufacturerCode = itemManufacturerCode;
	}
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}
	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	


}
