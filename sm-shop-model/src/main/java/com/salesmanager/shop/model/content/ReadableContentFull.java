package com.salesmanager.shop.model.content;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.entity.Entity;

@Deprecated
public class ReadableContentFull extends Entity {
	
	private String code;
	private boolean visible;
	private String contentType;
	
	private boolean isDisplayedInMenu;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ContentDescriptionEntity> descriptions = new ArrayList<ContentDescriptionEntity>();
	public List<ContentDescriptionEntity> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<ContentDescriptionEntity> descriptions) {
		this.descriptions = descriptions;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
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

}
