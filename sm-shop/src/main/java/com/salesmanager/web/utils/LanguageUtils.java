package com.salesmanager.web.utils;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.elasticsearch.common.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.constants.Constants;

@Component
public class LanguageUtils {
	
	private static final Language Language = null;
	@Inject
	LanguageService languageService;
	
	/**
	 * Determines request language based on store rules
	 * @param request
	 * @return
	 */
	public Language getRequestLanguage(HttpServletRequest request, HttpServletResponse response) {
		
		Locale locale = null;
		
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
		

		if(language==null) {
			try {
				
					locale = LocaleContextHolder.getLocale();//should be browser locale
				
				
					MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.MERCHANT_STORE);
					if(store!=null) {
						language = store.getDefaultLanguage();
						if(language!=null) {
							locale = languageService.toLocale(language);
							if(locale!=null) {
								LocaleContextHolder.setLocale(locale);
							}
							request.getSession().setAttribute(Constants.LANGUAGE, language);
					}
				
					if(language==null) {
						language = languageService.toLanguage(locale);
						request.getSession().setAttribute(Constants.LANGUAGE, language);
					}
				
				}

			} catch(Exception e) {
				if(language==null) {
					try {
						language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
					} catch(Exception ignore) {}
				}
			}
		} else {
			
			
			Locale localeFromContext = LocaleContextHolder.getLocale();//should be browser locale
			if(!language.getCode().equals(localeFromContext.getLanguage())) {
				//get locale context
				language = languageService.toLanguage(localeFromContext);
			}

		}
		
		locale = languageService.toLocale(language);
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if(localeResolver!=null) {
			localeResolver.setLocale(request, response, locale);
		}
		response.setLocale(locale);

		return language;
	}
	
	/**
	 * Should be used by rest web services
	 * @param request
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public Language getRESTLanguage(HttpServletRequest request, MerchantStore store) throws Exception {
		
		Validate.notNull(request,"HttpServletRequest must not be null");
		Validate.notNull(store,"MerchantStore must not be null");

		Language language = null;
		
		
		String lang = request.getParameter(Constants.LANG);
		
		if(StringUtils.isBlank(lang)) {
			//try with HttpSession
			language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
			if(language==null) {
				language = store.getDefaultLanguage();
			}
			
			if(language==null) {
				language = languageService.defaultLanguage();
			}
		} else {
			language = languageService.getByCode(lang);
			if(language==null) {
				language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
				if(language==null) {
					language = store.getDefaultLanguage();
				}
				
				if(language==null) {
					language = languageService.defaultLanguage();
				}
			}
		}

		return language;
	}

}
