package com.salesmanager.core.business.modules.cms.content;


import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.common.ImageRemove;
import com.salesmanager.core.model.content.FileContentType;

public interface ContentImageRemove extends ImageRemove {



  void removeImage(final String merchantStoreCode, final FileContentType imageContentType,
      final String imageName) throws ServiceException;

}
