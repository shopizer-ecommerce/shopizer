package com.salesmanager.core.business.reference.language.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.reference.language.dao.LanguageDao;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.model.Language_;
import com.salesmanager.core.utils.CacheUtils;

@Service("languageService")
public class LanguageServiceImpl extends SalesManagerEntityServiceImpl<Integer, Language>
	implements LanguageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);
	
	@Autowired
	private CacheUtils cache;
	
	@Autowired
	public LanguageServiceImpl(LanguageDao languageDao) {
		super(languageDao);
	}
	
	@Override
	public Language getByCode(String code) throws ServiceException {
		return getByField(Language_.code, code);
	}
	
	@Override
	public Locale toLocale(Language language) {
		return new Locale(language.getCode());
	}
	
	@Override
	public Language toLanguage(Locale locale) {
		
		try {
			Language lang = getLanguagesMap().get(locale.getLanguage());
			return lang;
		} catch (Exception e) {
			LOGGER.error("Cannot convert locale " + locale.getLanguage() + " to language");
		}
		
		return null;

	}
	
	@Override
	public Map<String,Language> getLanguagesMap() throws ServiceException {
		
		List<Language> langs = this.getLanguages();

		Map<String,Language> returnMap = new LinkedHashMap<String,Language>();
		
		for(Language lang : langs) {
			
			returnMap.put(lang.getCode(), lang);
			
		}
		
		return returnMap;
		
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Language> getLanguages() throws ServiceException {
		
		
		List<Language> langs = null;
		try {
			
			//CacheUtils cacheUtils = CacheUtils.getInstance();
			
			langs = (List<Language>) cache.getFromCache("LANGUAGES");

		
		
			if(langs==null) {
				langs = this.list();
				cache.putInCache(langs, "LANGUAGES");
			}
			
			
		
		
		
		} catch (Exception e) {
			LOGGER.error("getCountries()", e);
		}
		
		return langs;
		
	}

}
