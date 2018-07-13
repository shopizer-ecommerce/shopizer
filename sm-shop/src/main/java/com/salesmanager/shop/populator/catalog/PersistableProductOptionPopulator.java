package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;


public class PersistableProductOptionPopulator extends
    AbstractDataPopulator<PersistableProductOption, ProductOption> {

  private LanguageService languageService;

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }

  @Override
  public ProductOption populate(PersistableProductOption source,
      ProductOption target, MerchantStore store, Language language) throws ServiceException {

    Validate.notNull(languageService, "Requires to set LanguageService");

    target.setMerchantStore(store);
    target.setProductOptionSortOrder(source.getOrder());
    target.setCode(source.getCode());

    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      Set<com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription> descriptions = new HashSet<com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription>();
      for (ProductOptionDescription desc : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription description = new com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription();
        Language lang = languageService.getByCode(desc.getLanguage());
        description.setLanguage(lang);
        description.setName(desc.getName());
        description.setTitle(desc.getTitle());
        description.setProductOption(target);
        descriptions.add(description);
      }
      target.setDescriptions(descriptions);
    }

    return target;
  }

  @Override
  protected ProductOption createTarget() {
    return null;
  }
}
