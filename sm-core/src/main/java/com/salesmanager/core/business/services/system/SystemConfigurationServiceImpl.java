package com.salesmanager.core.business.services.system;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.system.SystemConfigurationRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.SystemConfiguration;

@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SystemConfiguration> implements
		SystemConfigurationService {

	
	private SystemConfigurationRepository systemConfigurationReposotory;
	
	@Inject
	public SystemConfigurationServiceImpl(
			SystemConfigurationRepository systemConfigurationReposotory) {
			super(systemConfigurationReposotory);
			this.systemConfigurationReposotory = systemConfigurationReposotory;
	}
	
	public SystemConfiguration getByKey(String key) throws ServiceException {
		return systemConfigurationReposotory.findByKey(key);
	}
	



}
