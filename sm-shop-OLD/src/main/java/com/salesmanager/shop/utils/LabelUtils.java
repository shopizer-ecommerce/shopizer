package com.salesmanager.shop.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class LabelUtils implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}
	
	public String getMessage(String key, Locale locale) {
		return applicationContext.getMessage(key, null, locale);
	}
	
	public String getMessage(String key, Locale locale, String defaultValue) {
		try {
			return applicationContext.getMessage(key, null, locale);
		} catch(Exception ignore) {}
		return defaultValue;
	}
	
	public String getMessage(String key, String[] args, Locale locale) {
		return applicationContext.getMessage(key, args, locale);
	}

}
