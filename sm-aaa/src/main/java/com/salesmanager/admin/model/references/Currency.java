package com.salesmanager.admin.model.references;

import com.salesmanager.admin.model.AbstractModel;

public class Currency extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private int supported;
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
	public int getSupported() {
		return supported;
	}
	public void setSupported(int supported) {
		this.supported = supported;
	}

}
