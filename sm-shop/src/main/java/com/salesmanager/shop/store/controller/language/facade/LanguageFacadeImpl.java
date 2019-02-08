package com.salesmanager.shop.store.controller.language.facade;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class LanguageFacadeImpl implements LanguageFacade {

  @Inject
  private LanguageService languageService;

  @Override
  public List<Language> getLanguages() {
    try{
      List<Language> languages = languageService.getLanguages();
      if (languages.isEmpty()) {
        throw new ResourceNotFoundException("No languages found");
      }
      return languages;
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }

  }
}
