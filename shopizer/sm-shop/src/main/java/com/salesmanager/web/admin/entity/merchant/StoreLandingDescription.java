package com.salesmanager.web.admin.entity.merchant;

import java.io.Serializable;

import com.salesmanager.core.business.reference.language.model.Language;

public class StoreLandingDescription implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String keywords;
	private String homePageContent;
	
	
	private Language language;

	
	
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setHomePageContent(String homePageContent) {
		this.homePageContent = homePageContent;
	}

	public String getHomePageContent() {
		return homePageContent;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}

}
