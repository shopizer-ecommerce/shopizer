package com.salesmanager.core.business.system.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.system.model.SystemConfiguration;

public interface SystemConfigurationService extends
		SalesManagerEntityService<Long, SystemConfiguration> {
	
	SystemConfiguration getByKey(String key) throws ServiceException;

}
