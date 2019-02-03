package com.salesmanager.shop.store.controller.optin;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface OptinConverter<S,T> {

  T convertTo(S source, MerchantStore merchantStore, Language language);

  S convertFrom(T source, MerchantStore merchantStore, Language language);
}
