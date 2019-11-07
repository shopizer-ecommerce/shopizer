package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionFull;

@Component
public class ReadableProductOptionMapper implements Mapper<ProductOption, ReadableProductOptionEntity> {

  @Override
  public ReadableProductOptionEntity convert(ProductOption source, MerchantStore store,
      Language language) {
    ReadableProductOptionEntity destination = new ReadableProductOptionEntity();
    return convert(source, destination, store, language);
  }


  @Override
  public ReadableProductOptionEntity convert(ProductOption source,
      ReadableProductOptionEntity destination, MerchantStore store, Language language) {
    ReadableProductOptionEntity readableProductOption = new ReadableProductOptionEntity();
    if(language == null) {
      readableProductOption = new ReadableProductOptionFull();
      List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription> descriptions = new ArrayList<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription>();
      for(ProductOptionDescription desc : source.getDescriptions()) {
          com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription d = this.description(desc);
          descriptions.add(d);
      }
      ((ReadableProductOptionFull)readableProductOption).setDescriptions(descriptions);
    } else {
      readableProductOption = new ReadableProductOptionEntity();
      if(!CollectionUtils.isEmpty(source.getDescriptions())) {
        for(ProductOptionDescription desc : source.getDescriptions()) {
          if(desc != null && desc.getLanguage()!= null && desc.getLanguage().getId() == language.getId()) {
            com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription d = this.description(desc);
            readableProductOption.setDescription(d);
          }
        }
      }
    }
    
    readableProductOption.setCode(source.getCode());
    readableProductOption.setId(source.getId());
    readableProductOption.setType(source.getProductOptionType());
    
    
    return readableProductOption;
  }



  com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription description(ProductOptionDescription description) {
    com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription desc = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription();
    desc.setDescription(description.getDescription());
    desc.setName(description.getName());
    desc.setId(description.getId());
    desc.setLanguage(description.getLanguage().getCode());
    return desc;
  }

}