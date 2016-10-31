package com.salesmanager.shop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class AppConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);
	public Properties properties;
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public AppConfiguration() {}
	
	public String getProperty(String propertyKey) {
		
		if(properties!=null) {
			return properties.getProperty(propertyKey);
		} else {
			LOGGER.warn("Application properties are not loaded");
			return null;
		}
		
		
	}

}
