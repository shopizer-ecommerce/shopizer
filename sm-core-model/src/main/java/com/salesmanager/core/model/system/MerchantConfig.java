package com.salesmanager.core.model.system;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class MerchantConfig implements Serializable, JSONAware {
	

	/**
	 * TODO
	 * Add a generic key value in order to allow the creation of configuration
	 * on the fly from the client application and read from a key value map
	 */
	
	private static final long serialVersionUID = 1L;
	private boolean displayCustomerSection =false;
	private boolean displayContactUs =false;
	private boolean displayStoreAddress = false;
	private boolean displayAddToCartOnFeaturedItems = false;
	private boolean displayCustomerAgreement = false;
	private boolean allowPurchaseItems = true;
	private boolean displaySearchBox = true;
	private boolean testMode = false;
	private boolean debugMode = false;
	
	/** Store default search json config **/
	private Map<String,Boolean> useDefaultSearchConfig= new HashMap<String,Boolean>();//language code | true or false
	private Map<String,String> defaultSearchConfigPath= new HashMap<String,String>();//language code | file path

	@SuppressWarnings("unchecked")
	@Override
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("displayCustomerSection", this.isDisplayCustomerSection());
		data.put("displayContactUs", this.isDisplayContactUs());
		data.put("displayStoreAddress", this.isDisplayStoreAddress());
		data.put("displayAddToCartOnFeaturedItems", this.isDisplayAddToCartOnFeaturedItems());
		data.put("displayCustomerAgreement", this.isDisplayCustomerAgreement());
		data.put("allowPurchaseItems", this.isAllowPurchaseItems());
		data.put("displaySearchBox", this.displaySearchBox);
		data.put("testMode", this.isTestMode());
		data.put("debugMode", this.isDebugMode());
		
		if(useDefaultSearchConfig!=null) {
			JSONObject obj = new JSONObject();
			for(String key : useDefaultSearchConfig.keySet()) {
				Boolean val = (Boolean)useDefaultSearchConfig.get(key);
				if(val!=null) {
					obj.put(key,val);
				}
			}
			data.put("useDefaultSearchConfig", obj);
		}
		
		if(defaultSearchConfigPath!=null) {
			JSONObject obj = new JSONObject();
			for(String key : defaultSearchConfigPath.keySet()) {
				String val = (String)defaultSearchConfigPath.get(key);
				if(!StringUtils.isBlank(val)) {
					obj.put(key, val);
				}
			}
			data.put("defaultSearchConfigPath", obj);
		}
		
		
		return data.toJSONString();
	}

	public void setDisplayCustomerSection(boolean displayCustomerSection) {
		this.displayCustomerSection = displayCustomerSection;
	}

	public boolean isDisplayCustomerSection() {
		return displayCustomerSection;
	}

	public void setDisplayContactUs(boolean displayContactUs) {
		this.displayContactUs = displayContactUs;
	}

	public boolean isDisplayContactUs() {
		return displayContactUs;
	}

	public boolean isDisplayStoreAddress() {
		return displayStoreAddress;
	}

	public void setDisplayStoreAddress(boolean displayStoreAddress) {
		this.displayStoreAddress = displayStoreAddress;
	}

	public void setUseDefaultSearchConfig(Map<String,Boolean> useDefaultSearchConfig) {
		this.useDefaultSearchConfig = useDefaultSearchConfig;
	}

	public Map<String,Boolean> getUseDefaultSearchConfig() {
		return useDefaultSearchConfig;
	}

	public void setDefaultSearchConfigPath(Map<String,String> defaultSearchConfigPath) {
		this.defaultSearchConfigPath = defaultSearchConfigPath;
	}

	public Map<String,String> getDefaultSearchConfigPath() {
		return defaultSearchConfigPath;
	}

	public void setDisplayAddToCartOnFeaturedItems(
			boolean displayAddToCartOnFeaturedItems) {
		this.displayAddToCartOnFeaturedItems = displayAddToCartOnFeaturedItems;
	}

	public boolean isDisplayAddToCartOnFeaturedItems() {
		return displayAddToCartOnFeaturedItems;
	}

	public boolean isDisplayCustomerAgreement() {
		return displayCustomerAgreement;
	}

	public void setDisplayCustomerAgreement(boolean displayCustomerAgreement) {
		this.displayCustomerAgreement = displayCustomerAgreement;
	}

	public boolean isAllowPurchaseItems() {
		return allowPurchaseItems;
	}

	public void setAllowPurchaseItems(boolean allowPurchaseItems) {
		this.allowPurchaseItems = allowPurchaseItems;
	}

	public boolean isDisplaySearchBox() {
		return displaySearchBox;
	}

	public void setDisplaySearchBox(boolean displaySearchBox) {
		this.displaySearchBox = displaySearchBox;
	}

	public boolean isTestMode() {
		return testMode;
	}

	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

}
