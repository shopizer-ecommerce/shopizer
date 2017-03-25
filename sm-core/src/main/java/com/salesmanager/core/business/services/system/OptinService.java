package com.salesmanager.core.business.services.system;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.system.optin.Optin;

public interface OptinService extends SalesManagerEntityService<Long, Optin>{
	List<Optin> findByMerchant(Integer storeId);
	Optin findByCode(String code);
}
