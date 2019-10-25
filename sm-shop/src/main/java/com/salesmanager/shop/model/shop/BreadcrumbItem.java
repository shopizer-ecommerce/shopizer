package com.salesmanager.shop.model.shop;

import java.io.Serializable;

public class BreadcrumbItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String label;
	private String url;
	private BreadcrumbItemType itemType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public BreadcrumbItemType getItemType() {
		return itemType;
	}
	public void setItemType(BreadcrumbItemType itemType) {
		this.itemType = itemType;
	}

}
