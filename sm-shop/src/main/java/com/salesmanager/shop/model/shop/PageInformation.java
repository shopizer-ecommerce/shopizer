package com.salesmanager.shop.model.shop;

import java.io.Serializable;

public class PageInformation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pageTitle;
	private String pageDescription;
	private String pageKeywords;
	private String pageUrl;
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}
	public String getPageKeywords() {
		return pageKeywords;
	}
	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getPageUrl() {
		return pageUrl;
	}

}
