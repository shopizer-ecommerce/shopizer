package com.salesmanager.core.business.services.system;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.system.optin.CustomerOptin;


public interface CustomerOptinService extends SalesManagerEntityService<Long, CustomerOptin>{
	List<CustomerOptin> findByCode(Integer storeId, String code);
}
