package com.salesmanager.shop.store.controller.optin;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.system.PersistableOptin;
import com.salesmanager.shop.model.system.ReadableOptin;

public interface OptinFacade {

  ReadableOptin create(PersistableOptin persistableOptin, MerchantStore merchantStore, Language language);
}
