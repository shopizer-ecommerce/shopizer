package com.salesmanager.core.business.modules.cms.content;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.OutputContentFile;

import java.util.List;


/**
 * Methods to retrieve the static content from the CMS
 *
 * @author Carl Samson
 */
public interface FileGet {

    OutputContentFile getFile(final String merchantStoreCode, FileContentType fileContentType, String contentName) throws ServiceException;

    List<String> getFileNames(final String merchantStoreCode, FileContentType fileContentType) throws ServiceException;

    List<OutputContentFile> getFiles(final String merchantStoreCode, FileContentType fileContentType) throws ServiceException;
}
