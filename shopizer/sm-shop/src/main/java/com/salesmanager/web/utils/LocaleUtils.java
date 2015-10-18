package com.salesmanager.web.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.admin.controller.categories.CategoryController;

public class LocaleUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	public static Locale getLocale(Language language) {
		
		return new Locale(language.getCode());
		
	}
	
	/**
	 * Creates a Locale object for currency format only with country code
	 * This method ignoes the language
	 * @param store
	 * @return
	 */
	public static Locale getLocale(MerchantStore store) {
		
		Locale defaultLocale = com.salesmanager.core.constants.Constants.DEFAULT_LOCALE;
		Locale[] locales = Locale.getAvailableLocales();
		for(int i = 0; i< locales.length; i++) {
			Locale l = locales[i];
			try {
				if(l.getISO3Country().equals(store.getCurrency().getCode())) {
					defaultLocale = l;
					break;
				}
			} catch(Exception e) {
				LOGGER.error("An error occured while getting ISO code for locale " + l.toString());
			}
		}
		
		return defaultLocale;
		
	}
	
/*	public static Language getRequestLanguage(HttpServletRequest request) {
		
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
		if(language==null) {
			
			Locale locale = LocaleContextHolder.getLocale();
			if(locale!=null) {
				
			}
			
			MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.MERCHANT_STORE);
			if(store!=null) {
				language = store.getDefaultLanguage();
			}
		}
		return language;
		
	}*/

}
