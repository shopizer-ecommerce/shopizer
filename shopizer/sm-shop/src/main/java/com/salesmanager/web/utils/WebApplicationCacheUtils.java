package com.salesmanager.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.utils.CacheUtils;

@Component
public class WebApplicationCacheUtils {
	
	@Autowired
	private CacheUtils cache;
	
	public Object getFromCache(String key) throws Exception {
		return cache.getFromCache(key);
	}
	
	public void putInCache(String key, Object object) throws Exception {
		cache.putInCache(object, key);
	}

}
