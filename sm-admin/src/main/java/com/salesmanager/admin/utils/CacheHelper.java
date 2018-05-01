package com.salesmanager.admin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.salesmanager.admin.components.references.ReferencesLoader;
import com.salesmanager.admin.model.references.Country;
import com.salesmanager.admin.model.references.Language;


/**
 * Manage all Cache
 * @author c.samson
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CacheHelper {
	
	private CacheManager cacheManager;
	private Cache languages;
	private Cache country;
	private Cache currency;
	
	@Inject
	ReferencesLoader references;
	
	
	private void buildCacheHelper() {

		
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().build();
              cacheManager.init();

			CacheConfiguration<String, ArrayList> noExpirationCacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, ArrayList.class,
            	        ResourcePoolsBuilder.heap(100))
            	    .withExpiry(ExpiryPolicy.NO_EXPIRY)
            	    .build();
              

              languages = cacheManager.createCache(Constants.Cache.LANGUAGE, noExpirationCacheConfiguration);
              country = cacheManager.createCache(Constants.Cache.COUNTRY, noExpirationCacheConfiguration);
              currency = cacheManager.createCache(Constants.Cache.CURRENCY, noExpirationCacheConfiguration);

	}
	

	public CacheHelper() {
		buildCacheHelper();
	}
	
	@SuppressWarnings("unchecked")
	public List<Language> getLanguages(Locale locale) throws Exception {
		
		String isoCode = locale.getLanguage();
		
		List<Language> langs =  (List<Language>)languages.get(isoCode);
		if(CollectionUtils.isEmpty(langs)) {
			langs = references.loadLanguages();
			for(Language lang : langs) {
				Locale l = new Locale(lang.getCode());
				if(l != null) {
					lang.setName(l.getDisplayName());
				}
			}
			languages.put(isoCode, langs);
		}
		return langs;
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> getCountry(Locale locale) throws Exception {
		
		String isoCode = locale.getLanguage();
		
		List<Country> listCountry =  (List<Country>)country.get(isoCode);
		if(CollectionUtils.isEmpty(listCountry)) {
			listCountry = references.loadCountry(locale);
			country.put(isoCode, listCountry);
		}
		return listCountry;

	}

}
