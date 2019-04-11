package com.salesmanager.shop.store.controller.converter;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface Converter<S, T> {

  T convert(S source, MerchantStore store, Language language);

}
