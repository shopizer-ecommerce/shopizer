package com.salesmanager.core.business.system.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantConfiguration;
import com.salesmanager.core.business.system.model.MerchantConfigurationType;

public interface MerchantConfigurationDao extends SalesManagerEntityDao<Long, MerchantConfiguration> {

	
	
	MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store);

	List<MerchantConfiguration> getMerchantConfigurations(MerchantStore store);

	List<MerchantConfiguration> listByType(MerchantConfigurationType type,
			MerchantStore store);
	
}
