package com.salesmanager.core.business.system.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.system.model.IntegrationModule;

public interface ModuleConfigurationDao extends SalesManagerEntityDao<Long, IntegrationModule> {

	List<IntegrationModule> getModulesConfiguration(String module);

	IntegrationModule getByCode(String moduleCode);

}
