package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionMapper;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValueList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionFacade;

@Service
public class ProductOptionFacadeImpl implements ProductOptionFacade {
  
  @Autowired
  private ProductOptionService productOptionService;
  
  @Autowired
  private ProductOptionValueService productOptionValueService;
  
  @Autowired
  private ReadableProductOptionMapper readableMapper;
  
  @Autowired
  private PersistableProductOptionMapper persistableeMapper;

  @Override
  public ReadableProductOptionEntity saveOption(PersistableProductOptionEntity option, MerchantStore store, Language language) {
    Validate.notNull(option, "ProductOption cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    
    ProductOption optionModel = new ProductOption();
    if(option.getId() != null && option.getId().longValue() > 0) {
      optionModel = productOptionService.getById(store, option.getId());
      if(optionModel == null) {
        throw new ResourceNotFoundException("ProductOption not found for if [" + option.getId() + "] and store [" + store.getCode() + "]");
      }
    }
    
    optionModel = persistableeMapper.convert(option, optionModel, store, language);
    try {
      productOptionService.saveOrUpdate(optionModel);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("An exception occured while saving ProductOption",e);
    }
    
    optionModel = productOptionService.getById(store, optionModel.getId());
    ReadableProductOptionEntity readable = readableMapper.convert(optionModel, store, language);
    return readable;
    
  }

  @Override
  public ReadableProductOptionValue saveOptionValue(PersistableProductOptionValue optionValue,
      MerchantStore store, Language language) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void deleteOption(Long optionId, MerchantStore store) {
    ProductOption optionModel = productOptionService.getById(store,optionId);
    if(optionModel == null) {
      throw new ResourceNotFoundException("ProductOption not found for if [" + optionId + "] and store [" + store.getCode() + "]");
    }
    try {
      productOptionService.delete(optionModel);
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("An exception occured while deleting ProductOption [" + optionId + "]",e);
    }
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
