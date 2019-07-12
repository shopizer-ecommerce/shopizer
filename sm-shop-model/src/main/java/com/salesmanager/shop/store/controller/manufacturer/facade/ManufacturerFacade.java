package com.salesmanager.shop.store.controller.manufacturer.facade;

import java.util.List;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;

/**
 * Manufacturer / brand / collection product grouping
 * @author carlsamson
 *
 */
public interface ManufacturerFacade {
  
  List<ReadableManufacturer> getByProductInCategory(MerchantStore store, Language language, Long categoryId);

}
