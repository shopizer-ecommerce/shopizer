package com.salesmanager.web.utils;

import com.salesmanager.web.constants.Constants;


/**
 * To be used when configured with an external server
 * @author c.samson
 *
 */
public class ImageFilePathUtils extends AbstractimageFilePath{
	
	private String basePath = Constants.STATIC_URI;

	@Override
	public String getBasePath() {
		// TODO Auto-generated method stub
		return basePath;
	}

	@Override
	public void setBasePath(String basePath) {
		// TODO Auto-generated method stub
		this.basePath = basePath;
	}

	@Override
	public String getContextPath() {
		return super.getProperties().getProperty(CONTEXT_PATH);
	}




	
}
