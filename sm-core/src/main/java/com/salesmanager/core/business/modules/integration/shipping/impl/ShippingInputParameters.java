package com.salesmanager.core.business.modules.integration.shipping.impl;

public class ShippingInputParameters {
	
	private String moduleName;
	private long weight;
	private long volume;
	private String country;
	private String province;
	private long distance;
	private long size;
	private int price;//integer should be rounded from BigBecimal
	private String priceQuote;
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public String getPriceQuote() {
		return priceQuote;
	}
	public void setPriceQuote(String priceQuote) {
		this.priceQuote = priceQuote;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" weight : ").append(this.getWeight());
		sb.append(" volume : ").append(this.getVolume())
		.append(" size : ").append(this.getSize())
		.append(" distance : ").append(this.getDistance())
		.append(" province : ").append(this.getProvince())
		.append(" price : ").append(this.getPrice())
		.append(" country : ").append(this.getCountry());
		return sb.toString();	
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}
