package com.salesmanager.core.modules.cms.content;

import java.util.List;

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.OutputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;


/**
 * Methods to retrieve the static content from the CMS
 * @author Carl Samson
 *
 */
public interface FileGet
{

	public OutputContentFile getFile(final String merchantStoreCode, FileContentType fileContentType, String contentName) throws ServiceException;
    public List<String> getFileNames(final String merchantStoreCode,FileContentType fileContentType) throws ServiceException;
    public List<OutputContentFile> getFiles(final String merchantStoreCode, FileContentType fileContentType) throws ServiceException;
}
