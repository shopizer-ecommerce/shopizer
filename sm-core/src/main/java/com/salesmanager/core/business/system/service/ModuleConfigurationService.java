package com.salesmanager.core.business.system.service;

import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.system.model.IntegrationModule;

public interface ModuleConfigurationService extends
		SalesManagerEntityService<Long, IntegrationModule> {

	List<IntegrationModule> getIntegrationModules(String module);

	IntegrationModule getByCode(String moduleCode);
	
	void createOrUpdateModule(String json) throws ServiceException;
	


}
