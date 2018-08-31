package com.salesmanager.shop.model.system;

import java.util.Date;

public class OptinEntity extends Optin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	private Date endDate;
	private String OptinType;
	private String store;
	private String code;
	private String description;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOptinType() {
		return OptinType;
	}
	public void setOptinType(String optinType) {
		OptinType = optinType;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
