package com.salesmanager.shop.store.facade.manufacturer;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.manufacturer.facade.ManufacturerFacade;

@Service("manufacturerFacade")
public class ManufacturerFacadeImpl implements ManufacturerFacade {

  @Inject
  private Mapper<Manufacturer, ReadableManufacturer> readableManufacturerConverter;


  @Autowired
  private ManufacturerService manufacturerService;
  
  @Autowired
  private CategoryService categoryService;

  @Override
  public List<ReadableManufacturer> getByProductInCategory(MerchantStore store, Language language,
      Long categoryId) {
    Validate.notNull(store,"MerchantStore cannot be null");
    Validate.notNull(language, "Language cannot be null");
    Validate.notNull(categoryId,"Category id cannot be null");
    
    Category category = categoryService.getById(categoryId);
    
    if(category == null) {
      throw new ResourceNotFoundException("Category with id [" + categoryId + "] not found");
    }
    
    if(category.getMerchantStore().getId().longValue() != store.getId().longValue()) {
      throw new UnauthorizedException("Merchant [" + store.getCode() + "] not authorized");
    }
    
    try {
      List<Manufacturer> manufacturers = manufacturerService.listByProductsInCategory(store, category, language);
      
      List<ReadableManufacturer> manufacturersList = manufacturers.stream()
        .map(manuf -> readableManufacturerConverter.convert(manuf, store, language))
        .collect(Collectors.toList());
      
      return manufacturersList;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }

  }


}
