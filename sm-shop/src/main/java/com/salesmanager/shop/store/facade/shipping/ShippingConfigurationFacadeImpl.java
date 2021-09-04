package com.salesmanager.shop.store.facade.shipping;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.configuration.PersistableConfiguration;
import com.salesmanager.shop.model.configuration.ReadableConfiguration;
import com.salesmanager.shop.store.controller.configurations.ConfigurationsFacade;

@Service("shippingConfigurationFacade")
public class ShippingConfigurationFacadeImpl implements ConfigurationsFacade {

	@Override
	public List<ReadableConfiguration> configurations(MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableConfiguration configuration(String module, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveConfiguration(PersistableConfiguration configuration, MerchantStore store) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteConfiguration(String module, MerchantStore store) {
		// TODO Auto-generated method stub
		
	}

}
