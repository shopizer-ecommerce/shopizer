package com.salesmanager.shop.model.system;

public class IntegrationModuleSummaryEntity extends IntegrationModuleEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean configured;
	private String image;

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

}
