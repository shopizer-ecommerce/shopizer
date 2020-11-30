package com.salesmanager.shop.model.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.salesmanager.shop.model.entity.Entity;

public class ConfigurationEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String key = null;
	private boolean active;
	private String value;
	private String type;
	private String code;
	private Map<String, String> keys = new HashMap<String, String>();
	private Map<String, List<String>> integrationOptions = new HashMap<String, List<String>>();
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, List<String>> getIntegrationOptions() {
		return integrationOptions;
	}
	public void setIntegrationOptions(Map<String, List<String>> integrationOptions) {
		this.integrationOptions = integrationOptions;
	}

}
