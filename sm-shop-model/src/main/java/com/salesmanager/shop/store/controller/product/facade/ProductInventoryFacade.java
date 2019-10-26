package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.inventory.PersistableInventory;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventoryList;

public interface ProductInventoryFacade {

  ReadableInventoryList getInventory(Long productId, MerchantStore store, String child, Language language, int page, int count);
  
  ReadableInventory get(Long inventoryId, MerchantStore store, Language language);
  
  ReadableInventory get(Long productId, Long inventoryId, MerchantStore store, Language language);
  
  ReadableInventory get(Long productId, String child, Language language);
  
  ReadableInventory add(Long productId, PersistableInventory inventory, MerchantStore store, Language language);
  
  void update(Long productId, PersistableInventory inventory, MerchantStore store, Language language);
  
  void delete(Long inventoryId, MerchantStore store);
  
  

}
