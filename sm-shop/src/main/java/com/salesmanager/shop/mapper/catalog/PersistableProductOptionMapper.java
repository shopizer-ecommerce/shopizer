package com.salesmanager.shop.mapper.catalog;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionEntity;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

@Component
public class PersistableProductOptionMapper implements Mapper<PersistableProductOptionEntity, ProductOption> {

  @Autowired
  private LanguageService languageService;



  ProductOptionDescription description(com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription description) throws Exception {
    Validate.notNull(description.getLanguage(),"description.language should not be null");
    ProductOptionDescription desc = new ProductOptionDescription();
    desc.setId(null);
    desc.setDescription(description.getDescription());
    desc.setName(description.getName());
    if(description.getId() != null && description.getId().longValue()>0) {
      desc.setId(description.getId());
    }
    Language lang = languageService.getByCode(description.getLanguage());
    desc.setLanguage(lang);
    return desc;
  }


  @Override
  public ProductOption convert(PersistableProductOptionEntity source, MerchantStore store,
      Language language) {
    ProductOption destination = new ProductOption();
    return merge(source, destination, store, language);
  }


  @Override
  public ProductOption merge(PersistableProductOptionEntity source, ProductOption destination,
                             MerchantStore store, Language language) {
    if(destination == null) {
      destination = new ProductOption();
    }
    
    try {

      if(!CollectionUtils.isEmpty(source.getDescriptions())) {
        for(com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription desc : source.getDescriptions()) {
          ProductOptionDescription description = null;
          if(!CollectionUtils.isEmpty(destination.getDescriptions())) {
            for(ProductOptionDescription d : destination.getDescriptions()) {
              if(!StringUtils.isBlank(desc.getLanguage()) && desc.getLanguage().equals(d.getLanguage().getCode())) {
            	  d.setDescription(desc.getDescription());
            	  d.setName(desc.getName());
            	  d.setTitle(desc.getTitle());
            	  description = d;
            	  break;
              } 
            }
          } 
          if(description == null) {
	          description = description(desc);
	          description.setProductOption(destination);
	          destination.getDescriptions().add(description);
          }
        }
      }
      
      destination.setCode(source.getCode());
      destination.setMerchantStore(store);
      destination.setProductOptionSortOrder(source.getOrder());
      destination.setProductOptionType(source.getType());
      destination.setReadOnly(source.isReadOnly());


      return destination;
      } catch (Exception e) {
        throw new ServiceRuntimeException("Error while converting product option", e);
      }
  }

}