/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;


public interface FolderPut {
	
	
  /**
   * Create folder on root or on specific path
   * @param merchantStoreCode
   * @param folderName
   * @param path
   * @throws ServiceException
   */
  public void addFolder(final String merchantStoreCode, String folderName, Optional<String> path)
      throws ServiceException;

}
