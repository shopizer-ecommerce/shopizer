package com.salesmanager.core.model.shipping;

public class PackageDetails {
	
	private double shippingWeight;
	private double shippingMaxWeight;
	private double shippingLength;
	private double shippingHeight;
	private double shippingWidth;
	private int shippingQuantity;
	private int treshold;
	
	private String itemName = "";
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getShippingWeight() {
		return shippingWeight;
	}
	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}
	public double getShippingMaxWeight() {
		return shippingMaxWeight;
	}
	public void setShippingMaxWeight(double shippingMaxWeight) {
		this.shippingMaxWeight = shippingMaxWeight;
	}
	public double getShippingLength() {
		return shippingLength;
	}
	public void setShippingLength(double shippingLength) {
		this.shippingLength = shippingLength;
	}
	public double getShippingHeight() {
		return shippingHeight;
	}
	public void setShippingHeight(double shippingHeight) {
		this.shippingHeight = shippingHeight;
	}
	public double getShippingWidth() {
		return shippingWidth;
	}
	public void setShippingWidth(double shippingWidth) {
		this.shippingWidth = shippingWidth;
	}
	public int getShippingQuantity() {
		return shippingQuantity;
	}
	public void setShippingQuantity(int shippingQuantity) {
		this.shippingQuantity = shippingQuantity;
	}
	public int getTreshold() {
		return treshold;
	}
	public void setTreshold(int treshold) {
		this.treshold = treshold;
	}

}
