package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import java.util.Optional;

import com.salesmanager.core.business.exception.ServiceException;

public interface FolderList {
	
	  public List<String> listFolders(final String merchantStoreCode, Optional<String> path)
		      throws ServiceException;

}
