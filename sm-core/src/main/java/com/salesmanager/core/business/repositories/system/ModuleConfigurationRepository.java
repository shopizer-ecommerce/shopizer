package com.salesmanager.core.business.repositories.system;

import com.salesmanager.core.model.system.IntegrationModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleConfigurationRepository extends JpaRepository<IntegrationModule, Long> {

    List<IntegrationModule> findByModule(String moduleName);

    IntegrationModule findByCode(String code);


}
