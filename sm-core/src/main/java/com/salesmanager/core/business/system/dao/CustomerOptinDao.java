package com.salesmanager.core.business.system.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.optin.model.CustomerOptin;
import com.salesmanager.core.business.system.optin.model.Optin;

public interface CustomerOptinDao extends SalesManagerEntityDao<Long, CustomerOptin> {


	List<CustomerOptin> listByMerchantAndOptin(MerchantStore store, Optin optin);
	
}
