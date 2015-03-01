package com.salesmanager.core.modules.cms.common;

import java.util.List;

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.OutputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;

public interface ImageGet
{

    public List<OutputContentFile> getImages( final String merchantStoreCode, FileContentType imageContentType )
        throws ServiceException;

}
