package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValueList;


/*
 * Options and Options values management independently from Product
 */
public interface ProductOptionFacade {
  
  
  ReadableProductOption createOption(PersistableProductOption option, MerchantStore store);
  
  ReadableProductOptionValue createOptionValue(PersistableProductOptionValue optionValue, MerchantStore store);
  
  void updateOption(Long optionId, PersistableProductOption option, MerchantStore store);
  
  void updateOptionValue(Long optionValueId, PersistableProductOptionValue optionValue, MerchantStore store);
  
  void deleteOption(Long optionId, MerchantStore store);
  
  void deleteOptionValue(Long optionValueId, MerchantStore store);
  
  ReadableProductOptionList options(MerchantStore store, Language language, String name, int page, int count);
  
  ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page, int count);

}
