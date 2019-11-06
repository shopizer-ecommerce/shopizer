package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValueList;


/*
 * Options and Options values management independently from Product
 */
public interface ProductOptionFacade {
  
  
  ReadableProductOptionEntity saveOption(PersistableProductOptionEntity option, MerchantStore store, Language language);
  
  ReadableProductOptionValue saveOptionValue(PersistableProductOptionValue optionValue, MerchantStore store, Language language);

  void deleteOption(Long optionId, MerchantStore store);
  
  void deleteOptionValue(Long optionValueId, MerchantStore store);
  
  ReadableProductOptionList options(MerchantStore store, Language language, String name, int page, int count);
  
  ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page, int count);

}