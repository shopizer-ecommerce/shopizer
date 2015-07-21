/**
 * 
 */
package com.salesmanager.core.modules.cms.content;

import java.util.List;

import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;


/**
 * @author Umesh Awasthi
 *
 */
public interface FilePut
{
    public void addFile(final String merchantStoreCode, InputContentFile inputStaticContentData) throws ServiceException;
    public void addFiles(final String merchantStoreCode, List<InputContentFile> inputStaticContentDataList) throws ServiceException;
}
