package com.salesmanager.web.admin.entity.web;

import java.util.List;

import com.salesmanager.core.business.system.model.MerchantConfiguration;

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
