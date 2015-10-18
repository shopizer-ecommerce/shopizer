package com.salesmanager.core.business.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.system.dao.SystemConfigurationDao;
import com.salesmanager.core.business.system.model.SystemConfiguration;
import com.salesmanager.core.business.system.model.SystemConfiguration_;

@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SystemConfiguration> implements
		SystemConfigurationService {

	
	private SystemConfigurationDao systemConfigurationDao;
	
	@Autowired
	public SystemConfigurationServiceImpl(
			SystemConfigurationDao systemConfigurationDao) {
			super(systemConfigurationDao);
			this.systemConfigurationDao = systemConfigurationDao;
	}
	
	public SystemConfiguration getByKey(String key) throws ServiceException {
		return super.getByField(SystemConfiguration_.key, key);
	}
	



}
