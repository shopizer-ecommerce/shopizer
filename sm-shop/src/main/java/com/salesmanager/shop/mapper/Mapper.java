package com.salesmanager.shop.mapper;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface Mapper<S, T> {

  T convert(S source, MerchantStore store, Language language);
  T merge(S source, T destination, MerchantStore store, Language language);
  

}
