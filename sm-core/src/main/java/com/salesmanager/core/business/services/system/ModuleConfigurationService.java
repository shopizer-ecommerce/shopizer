package com.salesmanager.core.business.services.system;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.system.IntegrationModule;

public interface ModuleConfigurationService extends
		SalesManagerEntityService<Long, IntegrationModule> {

	/**
	 * List all integration modules ready to be used by integrations such as payment and shipping
	 * @param module
	 * @return
	 */
	List<IntegrationModule> getIntegrationModules(String module);

	IntegrationModule getByCode(String moduleCode);
	
	void createOrUpdateModule(String json) throws ServiceException;
	


}
