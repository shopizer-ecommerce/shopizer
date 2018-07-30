package com.salesmanager.core.business.repositories.system;

import com.salesmanager.core.model.system.SystemConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, Long> {

    SystemConfiguration findByKey(String key);

}
