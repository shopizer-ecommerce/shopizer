/**
 * 
 */
package com.salesmanager.core.modules.cms.content;

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.generic.exception.ServiceException;


/**
 * @author Umesh Awasthi
 *
 */
public interface FileRemove
{
    public void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName) throws ServiceException;
    public void removeFiles(String merchantStoreCode) throws ServiceException;

}
