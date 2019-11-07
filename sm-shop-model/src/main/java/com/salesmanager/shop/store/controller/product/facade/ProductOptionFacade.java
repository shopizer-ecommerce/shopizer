package com.salesmanager.shop.store.controller.product.facade;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueList;


/*
 * Options and Options values management independently from Product
 */
public interface ProductOptionFacade {
  
  ReadableProductOptionEntity getOption(Long optionId, MerchantStore store, Language language);

  ReadableProductOptionValueEntity getOptionValue(Long optionValueId, MerchantStore store, Language language);

  ReadableProductOptionEntity saveOption(PersistableProductOptionEntity option, MerchantStore store, Language language);
  
  ReadableProductOptionValueEntity saveOptionValue(Optional<MultipartFile> image, PersistableProductOptionValueEntity optionValue, MerchantStore store, Language language);

  boolean optionExists(String code, MerchantStore store);
  
  boolean optionValueExists(String code, MerchantStore store);
  
  void deleteOption(Long optionId, MerchantStore store);
  
  void deleteOptionValue(Long optionValueId, MerchantStore store);
  
  ReadableProductOptionList options(MerchantStore store, Language language, String name, int page, int count);
  
  ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page, int count);

}