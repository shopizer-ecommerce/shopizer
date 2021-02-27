package com.salesmanager.shop.model.content.box;

import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.shop.model.content.common.Content;
import com.salesmanager.shop.model.content.common.ContentDescription;

public class ReadableContentBox extends Content {
	
	private ContentDescription description ;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReadableContentBox() {
		super.setContentType(ContentType.BOX.name());
	}

	public ContentDescription getDescription() {
		return description;
	}

	public void setDescription(ContentDescription description) {
		this.description = description;
	}

}
