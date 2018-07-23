
package com.salesmanager.shop.populator.manufacturer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;


/**
 * @author Carl Samson
 */


public class PersistableManufacturerPopulator extends
    AbstractDataPopulator<PersistableManufacturer, Manufacturer> {


  private LanguageService languageService;

  @Override
  public Manufacturer populate(PersistableManufacturer source, Manufacturer target,
      MerchantStore store, Language language) throws ServiceException {

    Validate.notNull(languageService, "Requires to set LanguageService");

    target.setMerchantStore(store);
    target.setCode(source.getCode());

    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      Set<com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription> descriptions = new HashSet<com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription>();
      for (ManufacturerDescription description : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription desc = new com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription();
        desc.setManufacturer(target);
        if (desc.getId() != null && desc.getId().longValue() > 0) {
          desc.setId(description.getId());
        }
        desc.setDescription(description.getDescription());
        desc.setName(description.getName());
        Language lang = languageService.getByCode(description.getLanguage());
        desc.setLanguage(lang);
        descriptions.add(desc);
      }
      target.setDescriptions(descriptions);
    }

    return target;
  }

  @Override
  protected Manufacturer createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

  public LanguageService getLanguageService() {
    return languageService;
  }

  public void setLanguageService(LanguageService languageService) {
    this.languageService = languageService;
  }
}
