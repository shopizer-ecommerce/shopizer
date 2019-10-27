package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionMapper;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValueList;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionFacade;

@Service
public class ProductOptionFacadeImpl implements ProductOptionFacade {
  
  @Autowired
  private ProductOptionService productOptionService;
  
  @Autowired
  private ProductOptionValueService productOptionValueService;
  
  @Autowired
  private ReadableProductOptionMapper readableMapper;

  @Override
  public ReadableProductOptionEntity createOption(PersistableProductOptionEntity option, MerchantStore store) {
    Validate.notNull(option, "ProductOption cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    return null;
  }

  @Override
  public ReadableProductOptionValue createOptionValue(PersistableProductOptionValue optionValue,
      MerchantStore store) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void updateOption(Long optionId, PersistableProductOption option, MerchantStore store) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateOptionValue(Long optionValueId, PersistableProductOptionValue optionValue,
      MerchantStore store) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteOption(Long optionId, MerchantStore store) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteOptionValue(Long optionValueId, MerchantStore store) {
    // TODO Auto-generated method stub

  }

  @Override
  public ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page, int count) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ReadableProductOptionList options(MerchantStore store, Language language, String name, int page,
      int count) {
    Validate.notNull(store, "MerchantStore should not be null");
    
    Page<ProductOption> options = productOptionService.getByMerchant(store, language, name, page, count);
    ReadableProductOptionList valueList = new ReadableProductOptionList();
    valueList.setTotalPages(options.getTotalPages());
    valueList.setRecordsTotal(options.getSize());
    
    List<ReadableProductOptionEntity> values = options.getContent().stream()
        .map(option -> readableMapper.convert(option, store, language))
        .collect(Collectors.toList());
    
    valueList.setOptions(values);
    
    return valueList;
  }

}
