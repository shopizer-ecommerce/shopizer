/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import com.salesmanager.core.business.exception.ServiceException;


public interface FolderPut {
  public void addFolder(final String merchantStoreCode, String folderName, String parent)
      throws ServiceException;

}
