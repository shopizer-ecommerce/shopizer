package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;

public class CustomerOptionEntity extends CustomerOption implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;
	private String code;
	private String type;//TEXT|SELECT|RADIO|CHECKBOX
	public void setOrder(int order) {
		this.order = order;
	}
	public int getOrder() {
		return order;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
