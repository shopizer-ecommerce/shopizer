package com.salesmanager.core.modules.cms.common;

import com.salesmanager.core.business.generic.exception.ServiceException;


public interface ImageRemove {
	
	
	public void removeImages(final String merchantStoreCode) throws ServiceException;
	
}
