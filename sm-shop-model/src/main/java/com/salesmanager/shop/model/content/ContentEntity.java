package com.salesmanager.shop.model.content;

import com.salesmanager.shop.model.entity.Entity;

@Deprecated
public class ContentEntity extends Entity {
	
	  private static final long serialVersionUID = 1L;
	  private String code;
	  private String contentType = "BOX";
	  private boolean isDisplayedInMenu;
	  private boolean visible;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isDisplayedInMenu() {
		return isDisplayedInMenu;
	}
	public void setDisplayedInMenu(boolean isDisplayedInMenu) {
		this.isDisplayedInMenu = isDisplayedInMenu;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
