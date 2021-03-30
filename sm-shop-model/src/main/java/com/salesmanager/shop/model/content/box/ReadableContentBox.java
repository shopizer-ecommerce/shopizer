package com.salesmanager.shop.model.content.box;

import com.salesmanager.shop.model.content.common.Content;
import com.salesmanager.shop.model.content.common.ContentDescription;

public class ReadableContentBox extends Content {
	
	private ContentDescription description ;
	private static final String BOX = "BOX";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReadableContentBox() {
		super.setContentType(BOX);
	}

	public ContentDescription getDescription() {
		return description;
	}

	public void setDescription(ContentDescription description) {
		this.description = description;
	}

}
