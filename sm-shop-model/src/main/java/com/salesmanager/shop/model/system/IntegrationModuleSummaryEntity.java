package com.salesmanager.shop.model.system;

import java.util.ArrayList;
import java.util.List;

public class IntegrationModuleSummaryEntity extends IntegrationModuleEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean configured;
	private String image;
	private String binaryImage;
	private List<String> requiredKeys = new ArrayList<String>();
	private String configurable = null;

	public List<String> getRequiredKeys() {
		return requiredKeys;
	}
	public void setRequiredKeys(List<String> requiredKeys) {
		this.requiredKeys = requiredKeys;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isConfigured() {
		return configured;
	}
	public void setConfigured(boolean configured) {
		this.configured = configured;
	}
	public String getBinaryImage() {
		return binaryImage;
	}
	public void setBinaryImage(String binaryImage) {
		this.binaryImage = binaryImage;
	}
	public String getConfigurable() {
		return configurable;
	}
	public void setConfigurable(String configurable) {
		this.configurable = configurable;
	}

}
