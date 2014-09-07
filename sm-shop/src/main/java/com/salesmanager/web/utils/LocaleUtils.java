package com.salesmanager.web.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.constants.Constants;

public class LocaleUtils {
	
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
			if(l.getISO3Country().equals(store.getCurrency().getCode())) {
				defaultLocale = l;
				break;
			}
		}
		
		return defaultLocale;
		
	}
	
	public static Language getRequestLanguage(HttpServletRequest request) {
		
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
		if(language==null) {
			MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.MERCHANT_STORE);
			if(store!=null) {
				language = store.getDefaultLanguage();
			}
		}
		return language;
		
	}

}
