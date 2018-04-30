package com.salesmanager.admin.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.salesmanager.admin.model.references.Language;



public class CacheHelper {
	
	private CacheManager cacheManager;
	
	
	private CacheHelper() {

		
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().build();
              cacheManager.init();
       
/*              squareNumberCache = cacheManager
                .createCache("squaredNumber", CacheConfigurationBuilder
                  .newCacheConfigurationBuilder(
                    Integer.class, Integer.class,
                    ResourcePoolsBuilder.heap(10)));*/
	}
	
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public static CacheHelper getInstance() {
		
		return new CacheHelper();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Language> getLanguages(String lang) {
		
		@SuppressWarnings("rawtypes")
		Cache<String, ArrayList> cacheLanguage = cacheManager.getCache(Constants.Cache.LANGUAGE, String.class, ArrayList.class);
		List<Language> langs =  cacheLanguage.get(lang);
		if(CollectionUtils.isEmpty(langs)) {
			//load languages from references
		}
		
		return null;
		
	}

}
