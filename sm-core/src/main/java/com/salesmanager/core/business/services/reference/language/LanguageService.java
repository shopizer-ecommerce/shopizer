package com.salesmanager.core.business.services.reference.language;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface LanguageService extends SalesManagerEntityService<Integer, Language> {

	Language getByCode(String code) throws ServiceException;

	Map<String, Language> getLanguagesMap() throws ServiceException;

	List<Language> getLanguages() throws ServiceException;

	Locale toLocale(Language language, MerchantStore store);

	Language toLanguage(Locale locale);
	
	Language defaultLanguage();
}
