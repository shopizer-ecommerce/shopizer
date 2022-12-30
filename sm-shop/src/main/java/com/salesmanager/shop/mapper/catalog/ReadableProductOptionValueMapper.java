package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueFull;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableProductOptionValueMapper implements Mapper<ProductOptionValue, ReadableProductOptionValue> {

  @Autowired
  @Qualifier("img")
  private ImageFilePath imageUtils;

  @Override
  public ReadableProductOptionValue merge(ProductOptionValue source, ReadableProductOptionValue destination,
                                                MerchantStore store, Language language) {
    ReadableProductOptionValue readableProductOptionValue = new ReadableProductOptionValue();
    if(language == null) {
    	readableProductOptionValue = new ReadableProductOptionValueFull();
      List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription> descriptions = new ArrayList<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription>();
      for(ProductOptionValueDescription desc : source.getDescriptions()) {
          com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription d = this.description(desc);
          descriptions.add(d);
      }
      ((ReadableProductOptionValueFull)readableProductOptionValue).setDescriptions(descriptions);
    } else {
    	readableProductOptionValue = new ReadableProductOptionValue();
      if(!CollectionUtils.isEmpty(source.getDescriptions())) {
        for(ProductOptionValueDescription desc : source.getDescriptions()) {
          if(desc != null && desc.getLanguage()!= null && desc.getLanguage().getId() == language.getId()) {
            com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription d = this.description(desc);
            readableProductOptionValue.setDescription(d);
          }
        }
      }
    }
    
    readableProductOptionValue.setCode(source.getCode());
    if(source.getId()!=null) {
    	readableProductOptionValue.setId(source.getId().longValue());
    }
    if(source.getProductOptionValueSortOrder()!=null) {
    	readableProductOptionValue.setOrder(source.getProductOptionValueSortOrder().intValue());
    }
    if(!StringUtils.isBlank(source.getProductOptionValueImage())) {
    	readableProductOptionValue.setImage(imageUtils.buildProductPropertyImageUtils(store, source.getProductOptionValueImage()));
    }
    
    return readableProductOptionValue;
  }



  com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription description(ProductOptionValueDescription description) {
    com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription desc = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription();
    desc.setDescription(description.getDescription());
    desc.setName(description.getName());
    desc.setId(description.getId());
    desc.setLanguage(description.getLanguage().getCode());
    return desc;
  }


@Override
public ReadableProductOptionValue convert(ProductOptionValue source, MerchantStore store, Language language) {
    ReadableProductOptionValue destination = new ReadableProductOptionValue();
    return merge(source, destination, store, language);
}

}