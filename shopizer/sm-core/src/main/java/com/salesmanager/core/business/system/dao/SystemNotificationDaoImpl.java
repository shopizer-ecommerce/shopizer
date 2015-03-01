package com.salesmanager.core.business.system.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.system.model.SystemNotification;

@Repository("systemNotificationDao")
public class SystemNotificationDaoImpl extends SalesManagerEntityDaoImpl<Long, SystemNotification>
		implements SystemNotificationDao {



}
