/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import com.salesmanager.core.business.exception.ServiceException;


public interface FolderRemove {
  public void removeFolder(final String merchantStoreCode, String folderName)
      throws ServiceException;

}
