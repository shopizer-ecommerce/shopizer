package com.salesmanager.shop.admin.model.web;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String url;
	private String icon;
	private String role;
	private int order;
	private List<Menu> menus = new ArrayList<Menu>();
	public String getCode() {
		return code;
	}
	@JsonProperty("code")  
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	@JsonProperty("url")  
	public void setUrl(String url) {
		this.url = url;
	}

	 

	public int getOrder() {
		return order;
	}
	@JsonProperty("order")  
	public void setOrder(int order) {
		this.order = order;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	@JsonProperty("menus")  
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	public String getRole() {
		return role;
	}
	@JsonProperty("role") 
	public void setRole(String role) {
		this.role = role;
	}

}
