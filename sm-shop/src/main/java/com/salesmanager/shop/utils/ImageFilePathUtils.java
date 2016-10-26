package com.salesmanager.shop.utils;

import com.salesmanager.shop.constants.Constants;

public class ImageFilePathUtils extends AbstractimageFilePath{
	
	private String basePath = Constants.STATIC_URI;

	@Override
	public String getBasePath() {
		return basePath;
	}

	@Override
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}




	
}
