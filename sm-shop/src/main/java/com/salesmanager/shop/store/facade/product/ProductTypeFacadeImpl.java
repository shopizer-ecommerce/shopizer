package com.salesmanager.shop.store.facade.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;

@Service("productTypeFacade")
public class ProductTypeFacadeImpl implements ProductTypeFacade {
  
  
  @Autowired 
  private ProductTypeService productTypeService;

  @Override
  public List<ReadableProductType> getByMerchant(String merchant, Language language) {
    try {
      List<ProductType> productTypes = productTypeService.getByMerchant(merchant, language);
      List<ReadableProductType> readableTypes = new ArrayList<ReadableProductType>();
      for(ProductType type : productTypes) {
        ReadableProductType readableType = new ReadableProductType();
        readableType.setId(type.getId());
        readableType.setCode(type.getCode());
        readableType.setName(type.getCode());
        readableTypes.add(readableType);
      }
      return readableTypes;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("An exception occured while getting product types for merchant[ " + merchant + "]");
    }
  }

}
