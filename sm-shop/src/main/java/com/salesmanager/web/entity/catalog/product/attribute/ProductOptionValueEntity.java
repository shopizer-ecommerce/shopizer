package com.salesmanager.web.entity.catalog.product.attribute;

import java.io.Serializable;

public class ProductOptionValueEntity extends ProductOptionValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order;
	private String code;
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

}
