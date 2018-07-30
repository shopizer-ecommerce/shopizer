package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.system.IntegrationModule;

import java.util.List;

public interface ModuleConfigurationService extends
        SalesManagerEntityService<Long, IntegrationModule> {

    List<IntegrationModule> getIntegrationModules(String module);

    IntegrationModule getByCode(String moduleCode);

    void createOrUpdateModule(String json) throws ServiceException;

}
