package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

public interface ProductTypeFacade {
  
  List<ReadableProductType> getByMerchant(MerchantStore store, Language language);
  
  ReadableProductType get(MerchantStore store, String code, Language language);
  
  void save(PersistableProductType type, MerchantStore store, Language language);
  
  void update(PersistableProductType type, String code, MerchantStore store, Language language);
  
  void delete(String code, MerchantStore store);

}
