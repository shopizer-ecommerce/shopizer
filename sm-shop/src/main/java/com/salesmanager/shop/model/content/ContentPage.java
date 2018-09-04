package com.salesmanager.shop.model.content;

public class ContentPage extends ContentPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isDisplayedInMenu;

	public boolean isDisplayedInMenu() {
		return isDisplayedInMenu;
	}

	public void setDisplayedInMenu(boolean isDisplayedInMenu) {
		this.isDisplayedInMenu = isDisplayedInMenu;
	}

}
