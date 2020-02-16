package com.salesmanager.core.business.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CoreConfiguration {
	

	public Properties properties = new Properties();
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreConfiguration.class);
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public CoreConfiguration() {}
	
	public String getProperty(String propertyKey) {
		
		return properties.getProperty(propertyKey);
		
		
	}
	
	public String getProperty(String propertyKey, String defaultValue) {
		
		String prop = defaultValue;
		try {
			prop = properties.getProperty(propertyKey);
		} catch(Exception e) {
			LOGGER.warn("Cannot find property " + propertyKey);
		}
		return prop;
		
		
	}

}
