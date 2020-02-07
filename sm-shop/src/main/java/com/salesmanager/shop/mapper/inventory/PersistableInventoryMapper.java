package com.salesmanager.shop.mapper.inventory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.catalog.product.inventory.PersistableInventory;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class PersistableInventoryMapper implements Mapper<PersistableInventory, ProductAvailability> {
  
  
  @Autowired
  private LanguageService languageService;

  @Override
  public ProductAvailability convert(PersistableInventory source, MerchantStore store,
      Language language) {
    ProductAvailability availability = new ProductAvailability();
    availability.setMerchantStore(store);
    return convert(source, availability, store, language);
    
  }

  @Override
  public ProductAvailability convert(PersistableInventory source, ProductAvailability destination,
      MerchantStore store, Language language) {
    Validate.notNull(destination, "Product availability cannot be null");
    
    try {

    if(destination == null) {
      destination = new ProductAvailability();
    }


    destination.setProductQuantity(source.getQuantity());
    destination.setProductQuantityOrderMin(source.getProductQuantityOrderMax());
    destination.setProductQuantityOrderMax(source.getProductQuantityOrderMin());
    destination.setAvailable(source.isAvailable());
    destination.setOwner(source.getOwner());
    if(!StringUtils.isBlank(source.getRegion())) {
      destination.setRegion(source.getRegion());
    } else {
      destination.setRegion(Constants.ALL_REGIONS);
    }
    
    destination.setRegionVariant(source.getRegionVariant());
    if(!StringUtils.isBlank(source.getDateAvailable())) {
      destination.setProductDateAvailable(DateUtil.getDate(source.getDateAvailable()));
    }

    for(PersistableProductPrice priceEntity : source.getPrices()) {
      
      ProductPrice price = new ProductPrice();
      price.setId(null);
      if(priceEntity.getId()!=null && priceEntity.getId().longValue()>0) {
    	  price.setId(priceEntity.getId());
      }
      Set<ProductPrice> prices = new HashSet<ProductPrice>();
      if(destination.getPrices()!=null) {
        for(ProductPrice pp : destination.getPrices()) {
          if(priceEntity.getId()!=null && priceEntity.getId().longValue()>0 && priceEntity.getId().longValue() == pp.getId().longValue()) {
            price = pp;
            price.setId(pp.getId());
          }
        }
      }

      price.setProductAvailability(destination);
      price.setDefaultPrice(priceEntity.isDefaultPrice());
      price.setProductPriceAmount(priceEntity.getOriginalPrice());
      price.setDefaultPrice(priceEntity.isDefaultPrice());
      price.setCode(priceEntity.getCode());
      price.setProductPriceSpecialAmount(priceEntity.getDiscountedPrice());
      if(priceEntity.getDiscountStartDate()!=null) {
          Date startDate = DateUtil.getDate(priceEntity.getDiscountStartDate());
          price.setProductPriceSpecialStartDate(startDate);
      }
      if(priceEntity.getDiscountEndDate()!=null) {
          Date endDate = DateUtil.getDate(priceEntity.getDiscountEndDate());
          price.setProductPriceSpecialEndDate(endDate);
      }
      //destination.getPrices().add(price);
      
      price.setProductAvailability(destination);
      
      java.util.List<com.salesmanager.shop.model.catalog.product.ProductPriceDescription> descriptions = priceEntity.getDescriptions();
      if(descriptions != null) {
        Set<ProductPriceDescription> descs = new HashSet<ProductPriceDescription>();
        for(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc : descriptions) {
          ProductPriceDescription description = null;
          if(!CollectionUtils.isEmpty(price.getDescriptions())) {
            for(ProductPriceDescription d : price.getDescriptions()) {
              if(desc.getId() != null && desc.getId().longValue() > 0 && desc.getId().longValue() == d.getId().longValue()) {
                desc.setId(d.getId());
              }
            }
          }
          description = getDescription(desc);
          description.setProductPrice(price);
          descs.add(description);
        }
        price.setDescriptions(descs);
      }
      prices.add(price);
      destination.setPrices(prices);
    }
    
    return destination;
    
    } catch(Exception e) {
      throw new ConversionRuntimeException(e);
    }

  }
  
  private ProductPriceDescription getDescription(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc) throws ConversionException {
    ProductPriceDescription target = new ProductPriceDescription();
    target.setDescription(desc.getDescription());
    target.setName(desc.getName());
    target.setTitle(desc.getTitle());
    target.setId(null);
    if(desc.getId()!=null && desc.getId().longValue()>0) {
      target.setId(desc.getId());
    }

    Language lang;
    try {
      lang = languageService.getByCode(desc.getLanguage());
      target.setLanguage(lang);
      if(lang==null) {
        throw new ConversionException("Language is null for code " + desc.getLanguage() + " use language ISO code [en, fr ...]");
    }
    } catch (ServiceException e) {
      throw new ConversionException(e);
    }

    return target;

  }

}
