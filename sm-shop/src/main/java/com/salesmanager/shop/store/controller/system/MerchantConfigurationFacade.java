package com.salesmanager.shop.store.controller.system;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.system.Configs;

public interface MerchantConfigurationFacade {

  Configs getMerchantConfig(MerchantStore merchantStore, Language language);

}
