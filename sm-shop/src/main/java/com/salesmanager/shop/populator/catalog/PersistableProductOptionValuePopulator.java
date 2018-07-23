package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;


/**
 * Converts a PersistableProductOptionValue to a ProductOptionValue model object
 *
 * @author Carl Samson
 */
public class PersistableProductOptionValuePopulator extends
    AbstractDataPopulator<PersistableProductOptionValue, ProductOptionValue> {


  private LanguageService languageService;

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }

  @Override
  public ProductOptionValue populate(PersistableProductOptionValue source,
      ProductOptionValue target, MerchantStore store, Language language)
      throws ServiceException {

    Validate.notNull(languageService, "Requires to set LanguageService");

    target.setMerchantStore(store);
    target.setProductOptionValueSortOrder(source.getOrder());
    target.setCode(source.getCode());

    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      Set<com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription> descriptions = new HashSet<com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription>();
      for (ProductOptionValueDescription desc : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription description = new com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription();
        Language lang = languageService.getByCode(desc.getLanguage());
        description.setLanguage(lang);
        description.setName(desc.getName());
        description.setTitle(desc.getTitle());
        description.setProductOptionValue(target);
        descriptions.add(description);
      }
      target.setDescriptions(descriptions);
    }

    return target;
  }

  @Override
  protected ProductOptionValue createTarget() {
    return null;
  }
}
