package com.salesmanager.core.modules.cms.content;


import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.modules.cms.common.ImageRemove;

public interface ContentImageRemove extends ImageRemove {
	
	
	
	public void removeImage(final String merchantStoreCode,final FileContentType imageContentType, final String imageName) throws ServiceException;

}
