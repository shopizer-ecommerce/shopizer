package com.salesmanager.shop.model.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationModuleConfiguration extends IntegrationModuleEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean defaultSelected;
	private Map<String, String> integrationKeys = new HashMap<String,String>();
	private Map<String, List<String>> integrationOptions = new HashMap<String, List<String>>();
	public boolean isDefaultSelected() {
		return defaultSelected;
	}
	public void setDefaultSelected(boolean defaultSelected) {
		this.defaultSelected = defaultSelected;
	}
	public Map<String, String> getIntegrationKeys() {
		return integrationKeys;
	}
	public void setIntegrationKeys(Map<String, String> integrationKeys) {
		this.integrationKeys = integrationKeys;
	}
	public Map<String, List<String>> getIntegrationOptions() {
		return integrationOptions;
	}
	public void setIntegrationOptions(Map<String, List<String>> integrationOptions) {
		this.integrationOptions = integrationOptions;
	}
}
