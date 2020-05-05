/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.InputContentFile;


/**
 * @author Umesh Awasthi
 *
 */
public interface FilePut {
  public void addFile(final String merchantStoreCode, InputContentFile inputStaticContentData)
      throws ServiceException;

  public void addFiles(final String merchantStoreCode,
      List<InputContentFile> inputStaticContentDataList) throws ServiceException;
}
