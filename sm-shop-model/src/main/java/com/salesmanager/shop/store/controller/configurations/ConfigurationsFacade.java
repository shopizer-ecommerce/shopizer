package com.salesmanager.shop.store.controller.configurations;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.configuration.PersistableConfiguration;
import com.salesmanager.shop.model.configuration.ReadableConfiguration;

public interface ConfigurationsFacade {
	
	List<ReadableConfiguration> configurations(MerchantStore store);
	
	ReadableConfiguration configuration(String module, MerchantStore store);
	
	void saveConfiguration(PersistableConfiguration configuration, MerchantStore store);
	
	void deleteConfiguration(String module, MerchantStore store);

}
