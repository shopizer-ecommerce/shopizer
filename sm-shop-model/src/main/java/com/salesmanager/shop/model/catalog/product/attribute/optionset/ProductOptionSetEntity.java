package com.salesmanager.shop.model.catalog.product.attribute.optionset;

import java.io.Serializable;

public class ProductOptionSetEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private boolean readOnly;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
