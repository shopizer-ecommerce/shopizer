package com.salesmanager.core.business.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.business.system.model.QIntegrationModule;

@Repository("integrationModuleDao")
public class ModuleConfigurationDaoImpl extends SalesManagerEntityDaoImpl<Long, IntegrationModule>
		implements ModuleConfigurationDao {


	@Override
	public List<IntegrationModule> getModulesConfiguration(String module) {
		
		
		
		QIntegrationModule qIntegrationModule = QIntegrationModule.integrationModule;

		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qIntegrationModule)
			.where(qIntegrationModule.module.eq(module));
		
		return query.list(qIntegrationModule);


	}
	
	@Override
	public IntegrationModule getByCode(String moduleCode) {
		
		
		
		QIntegrationModule qIntegrationModule = QIntegrationModule.integrationModule;

		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qIntegrationModule)
			.where(qIntegrationModule.code.eq(moduleCode));
		
		return query.uniqueResult(qIntegrationModule);


	}

}
