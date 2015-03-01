package com.salesmanager.core.business.system.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.system.model.SystemConfiguration;

@Repository("systemConfigurationDao")
public class SystemConfigurationDaoImpl extends SalesManagerEntityDaoImpl<Long, SystemConfiguration>
		implements SystemConfigurationDao {



}
