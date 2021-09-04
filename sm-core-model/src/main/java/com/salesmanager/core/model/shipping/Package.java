package com.salesmanager.core.model.shipping;

import java.io.Serializable;

public class Package implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public int getTreshold() {
		return treshold;
	}
	public void setTreshold(int treshold) {
		this.treshold = treshold;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getBoxWidth() {
		return boxWidth;
	}
	public void setBoxWidth(double boxWidth) {
		this.boxWidth = boxWidth;
	}
	public double getBoxHeight() {
		return boxHeight;
	}
	public void setBoxHeight(double boxHeight) {
		this.boxHeight = boxHeight;
	}
	public double getBoxLength() {
		return boxLength;
	}
	public void setBoxLength(double boxLength) {
		this.boxLength = boxLength;
	}
	public double getBoxWeight() {
		return boxWeight;
	}
	public void setBoxWeight(double boxWeight) {
		this.boxWeight = boxWeight;
	}
	public double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean isDefaultPackaging() {
		return defaultPackaging;
	}
	public void setDefaultPackaging(boolean defaultPackaging) {
		this.defaultPackaging = defaultPackaging;
	}
	public ShippingPackageType getShipPackageType() {
		return shipPackageType;
	}
	public void setShipPackageType(ShippingPackageType shipPackageType) {
		this.shipPackageType = shipPackageType;
	}
	private String code;
	private double boxWidth = 0;
	private double boxHeight = 0;
	private double boxLength = 0;
	private double boxWeight = 0;
	private double maxWeight = 0;	
	//private int shippingQuantity;
	private int treshold;
	private ShippingPackageType shipPackageType;
	private boolean defaultPackaging;

}
