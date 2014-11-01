package com.salesmanager.web.utils;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.constants.Constants;

@Component
public class LanguageUtils {
	
	@Inject
	LanguageService languageService;
	
	/**
	 * Determines request language based on store rules
	 * @param request
	 * @return
	 */
	public Language getRequestLanguage(HttpServletRequest request) {
		
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
		if(language==null) {
			try {
				
				Locale locale = LocaleContextHolder.getLocale();
				if(locale!=null) {
					language = languageService.toLanguage(locale);
				}
				
				if(language==null) {
					MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.MERCHANT_STORE);
					if(store!=null) {
						language = store.getDefaultLanguage();
					}
				}
				
				if(language!=null) {
					request.getSession().setAttribute(Constants.LANGUAGE, language);
				}
			} catch(Exception e) {
				if(language==null) {
					try {
						language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
					} catch(Exception ignore) {}
				}
			}
		}
		
		return language;
	}

}
