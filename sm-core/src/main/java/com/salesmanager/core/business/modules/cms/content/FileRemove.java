/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;


/**
 * @author Umesh Awasthi
 *
 */
public interface FileRemove {
  void removeFile(String merchantStoreCode, FileContentType staticContentType,
      String fileName, Optional<String> path) throws ServiceException;

  void removeFiles(String merchantStoreCode, Optional<String> path) throws ServiceException;

}
