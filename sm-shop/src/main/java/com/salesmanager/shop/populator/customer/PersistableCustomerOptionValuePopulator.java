package com.salesmanager.shop.populator.customer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.shop.model.customer.attribute.PersistableCustomerOptionValue;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;


public class PersistableCustomerOptionValuePopulator extends
    AbstractDataPopulator<PersistableCustomerOptionValue, CustomerOptionValue> {

  private LanguageService languageService;

  @Override
  public CustomerOptionValue populate(PersistableCustomerOptionValue source,
      CustomerOptionValue target, MerchantStore store, Language language)
      throws ServiceException {

    Validate.notNull(languageService, "Requires to set LanguageService");

    target.setCode(source.getCode());
    target.setMerchantStore(store);
    target.setSortOrder(source.getOrder());

    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      Set<com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription> descriptions = new HashSet<com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription>();
      for (CustomerOptionValueDescription desc : source.getDescriptions()) {
        com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription description = new com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription();
        Language lang = languageService.getByCode(desc.getLanguage());
        description.setLanguage(lang);
        description.setName(desc.getName());
        description.setTitle(desc.getTitle());
        description.setCustomerOptionValue(target);
        descriptions.add(description);
      }
      target.setDescriptions(descriptions);
    }
    return target;
  }

  @Override
  protected CustomerOptionValue createTarget() {
    return null;
  }

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }

}
