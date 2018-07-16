package com.salesmanager.core.business.services.reference.language;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.reference.language.LanguageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * https://samerabdelkafi.wordpress.com/2014/05/29/spring-data-jpa/
 *
 * @author c.samson
 */

@Service("languageService")
public class LanguageServiceImpl extends SalesManagerEntityServiceImpl<Integer, Language>
    implements LanguageService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);

  @Inject
  private CacheUtils cache;

  private LanguageRepository languageRepository;

  @Inject
  public LanguageServiceImpl(LanguageRepository languageRepository) {
    super(languageRepository);
    this.languageRepository = languageRepository;
  }

  @Override
  public Language getByCode(String code) throws ServiceException {
    return languageRepository.findByCode(code);
  }

  @Override
  public Locale toLocale(Language language, MerchantStore store) {

    // Default to english
    if (language == null) {
      language = new Language("en");
    }

    if (store != null) {
      String countryCode = store.getCountry().getIsoCode();

      //try to build valid language
      if ("CA".equals(countryCode) && language.getCode().equals("en")) {
        countryCode = "US";
      }

      return new Locale(language.getCode(), countryCode);
    } else {
      return new Locale(language.getCode());
    }
  }

  @Override
  public Language toLanguage(Locale locale) {

    try {
      Language lang = getLanguagesMap().get(locale.getLanguage());
      return lang;
    } catch (Exception e) {
      LOGGER.error("Cannot convert locale " + locale.getLanguage() + " to language");
    }

    return new Language(Constants.DEFAULT_LANGUAGE);
  }

  @Override
  public Map<String, Language> getLanguagesMap() throws ServiceException {

    List<Language> langs = this.getLanguages();
    Map<String, Language> returnMap = new LinkedHashMap<>();

    for (Language lang : langs) {
      returnMap.put(lang.getCode(), lang);
    }
    return returnMap;
  }


  @Override
  public List<Language> getLanguages() throws ServiceException {
    List<Language> languages;

    languages = (List<Language>) cache.getFromCache("LANGUAGES");
    if (languages == null) {
      languages = this.list();
      cache.putInCache(languages, "LANGUAGES");
    }

    return languages;
  }

  @Override
  public Language defaultLanguage() {
    return toLanguage(Locale.ENGLISH);
  }
}
