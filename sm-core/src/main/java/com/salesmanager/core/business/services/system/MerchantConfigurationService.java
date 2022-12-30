package com.salesmanager.core.business.services.system;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;

public interface MerchantConfigurationService extends
		SalesManagerEntityService<Long, MerchantConfiguration> {
	
	MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws ServiceException;
	
	void saveOrUpdate(MerchantConfiguration entity) throws ServiceException;

	List<MerchantConfiguration> listByStore(MerchantStore store)
			throws ServiceException;

	List<MerchantConfiguration> listByType(MerchantConfigurationType type,
			MerchantStore store) throws ServiceException;

	MerchantConfig getMerchantConfig(MerchantStore store)
			throws ServiceException;

	void saveMerchantConfig(MerchantConfig config, MerchantStore store)
			throws ServiceException;

}
