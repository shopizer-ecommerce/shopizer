package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.InputContentFile;

public interface ImagePut {


  void addImage(final String merchantStoreCode, InputContentFile image)
      throws ServiceException;

  void addImages(final String merchantStoreCode, List<InputContentFile> imagesList)
      throws ServiceException;

}
