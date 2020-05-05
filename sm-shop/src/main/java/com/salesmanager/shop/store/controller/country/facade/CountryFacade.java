package com.salesmanager.shop.store.controller.country.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.ReadableCountry;
import java.util.List;

public interface CountryFacade {
  List<ReadableCountry> getListCountryZones(Language language, MerchantStore merchantStore);
}
