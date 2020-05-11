package com.salesmanager.core.business.modules.order.total;

public class OrderTotalResponse {
	
	private Double discount = null;
	private String expiration;

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

}
