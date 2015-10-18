package com.salesmanager.core.business.system.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantLog;

public interface MerchantLogDao extends SalesManagerEntityDao<Long, MerchantLog> {


	List<MerchantLog> listByMerchant(MerchantStore store);
	
}
