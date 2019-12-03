package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.entity.Entity;

public class CetalogEntryEntity extends Entity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catalog;
	private boolean visible;
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
