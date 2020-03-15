package com.salesmanager.shop.admin.model.web;

import java.util.List;

import com.salesmanager.core.model.system.MerchantConfiguration;



public class ConfigListWrapper
{
	private List<MerchantConfiguration> merchantConfigs;

	public List<MerchantConfiguration> getMerchantConfigs()
	{
		return merchantConfigs;
	}

	public void setMerchantConfigs(List<MerchantConfiguration> merchantConfigs)
	{
		this.merchantConfigs = merchantConfigs;
	}

}
