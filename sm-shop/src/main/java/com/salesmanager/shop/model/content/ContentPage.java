package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.entity.ResourceUrlAccess;

public class ContentPage extends ContentPath implements ResourceUrlAccess {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isDisplayedInMenu;
	private String slug;
	private String code;

	public boolean isDisplayedInMenu() {
		return isDisplayedInMenu;
	}

	public void setDisplayedInMenu(boolean isDisplayedInMenu) {
		this.isDisplayedInMenu = isDisplayedInMenu;
	}

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
