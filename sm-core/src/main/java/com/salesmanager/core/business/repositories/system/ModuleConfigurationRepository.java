package com.salesmanager.core.business.repositories.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.system.IntegrationModule;

public interface ModuleConfigurationRepository extends JpaRepository<IntegrationModule, Long> {

	List<IntegrationModule> findByModule(String moduleName);
	
	IntegrationModule findByCode(String code);
	

}
