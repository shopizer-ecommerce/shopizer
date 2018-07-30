/**
 *
 */
package com.salesmanager.core.business.modules.cms.content;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;

/**
 * @author Umesh Awasthi
 */
public interface FileRemove {

    void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName) throws ServiceException;

    void removeFiles(String merchantStoreCode) throws ServiceException;

}
