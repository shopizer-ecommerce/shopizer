package com.salesmanager.core.business.modules.cms.common;

import com.salesmanager.core.business.exception.ServiceException;


public interface ImageRemove {

  void removeImages(final String merchantStoreCode) throws ServiceException;

}
