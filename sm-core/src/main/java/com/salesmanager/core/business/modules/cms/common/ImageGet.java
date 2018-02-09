package com.salesmanager.core.business.modules.cms.common;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.OutputContentFile;

public interface ImageGet
{

    public List<OutputContentFile> getImages( final String merchantStoreCode, FileContentType imageContentType )
        throws ServiceException;

}
