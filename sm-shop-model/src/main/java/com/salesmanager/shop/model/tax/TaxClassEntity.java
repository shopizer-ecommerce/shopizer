package com.salesmanager.shop.model.tax;

import javax.validation.constraints.Size;

import com.salesmanager.shop.model.entity.Entity;

public class TaxClassEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(min = 1, max = 10)
	private String code;
	private String name;
	private String store;
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
