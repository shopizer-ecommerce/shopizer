/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.InputContentFile;


/**
 * @author Umesh Awasthi
 *
 */

public interface FilePut {
	
  /**
   * Add file to folder
   * @param merchantStoreCode
   * @param path
   * @param inputStaticContentData
   * @throws ServiceException
   */
  void addFile(final String merchantStoreCode, Optional<String> path, InputContentFile inputStaticContentData)
      throws ServiceException;

  /**
   * Add files to folder
   * @param merchantStoreCode
   * @param path
   * @param inputStaticContentDataList
   * @throws ServiceException
   */
  void addFiles(final String merchantStoreCode,
      Optional<String> path, List<InputContentFile> inputStaticContentDataList) throws ServiceException;
}
