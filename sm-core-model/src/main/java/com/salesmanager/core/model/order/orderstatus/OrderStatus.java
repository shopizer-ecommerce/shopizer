package com.salesmanager.core.model.order.orderstatus;

public enum OrderStatus {
	
	ORDERED("ordered"),
	PROCESSED("processed"),
	DELIVERED("delivered"),
	REFUNDED("refunded"),
	CANCELED("canceled"),
	;
	
	private String value;
	
	private OrderStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
