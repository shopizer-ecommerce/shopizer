package com.salesmanager.core.modules.cms.content;

import java.util.List;

import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;

public interface ImagePut {
	
	
	public void addImage(final String merchantStoreCode, InputContentFile image) throws ServiceException;
	public void addImages(final String merchantStoreCode, List<InputContentFile> imagesList) throws ServiceException;

}
