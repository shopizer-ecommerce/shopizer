package com.salesmanager.shop.model.content.box;

import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.shop.model.content.common.Content;

public class ReadableContentBox extends Content {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReadableContentBox() {
		super.setContentType(ContentType.BOX.name());
	}

}
