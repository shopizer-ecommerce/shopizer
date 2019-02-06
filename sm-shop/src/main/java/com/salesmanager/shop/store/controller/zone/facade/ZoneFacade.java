package com.salesmanager.shop.store.controller.zone.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.shop.model.references.ReadableZone;
import java.util.List;

public interface ZoneFacade {

  List<ReadableZone> getZones(String countryCode, Language language, MerchantStore merchantStore);

}
