package com.salesmanager.shop.mapper.inventory;

import static com.salesmanager.core.business.utils.NumberUtils.isPositive;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    return merge(source, availability, store, language);
    
  }

  @Override
  public ProductAvailability merge(PersistableInventory source, ProductAvailability destination,
                                   MerchantStore store, Language language) {
    Validate.notNull(destination, "Product availability cannot be null");

    try {
    destination.setProductQuantity(source.getQuantity());
    destination.setProductQuantityOrderMin(source.getProductQuantityOrderMax());
    destination.setProductQuantityOrderMax(source.getProductQuantityOrderMin());
    destination.setAvailable(source.isAvailable());
    destination.setOwner(source.getOwner());

      String region = getRegion(source);
      destination.setRegion(region);
    
    destination.setRegionVariant(source.getRegionVariant());
    if(StringUtils.isNotBlank(source.getDateAvailable())) {
      destination.setProductDateAvailable(DateUtil.getDate(source.getDateAvailable()));
    }

    for(PersistableProductPrice priceEntity : source.getPrices()) {
      
      ProductPrice price = new ProductPrice();
      price.setId(null);

      if(isPositive(priceEntity.getId())) {
    	  price.setId(priceEntity.getId());
      }

//      Set<ProductPrice> productPrices = Optional.ofNullable(destination.getPrices()).orElse(Collections.emptySet());
//      productPrices.stream()
//              .filter()
      if(destination.getPrices()!=null) {
        for(ProductPrice pp : destination.getPrices()) {
          if(isPositive(priceEntity.getId()) && priceEntity.getId().equals(pp.getId())) {
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

      if(Objects.nonNull(priceEntity.getDiscountStartDate())) {
          Date startDate = DateUtil.getDate(priceEntity.getDiscountStartDate());
          price.setProductPriceSpecialStartDate(startDate);
      }
      if(Objects.nonNull(priceEntity.getDiscountEndDate())) {
          Date endDate = DateUtil.getDate(priceEntity.getDiscountEndDate());
          price.setProductPriceSpecialEndDate(endDate);
      }

      Set<ProductPriceDescription> descs = getProductPriceDescriptions(price, priceEntity.getDescriptions());
      price.setDescriptions(descs);
      Set<ProductPrice> prices = new HashSet<ProductPrice>();
      prices.add(price);
      destination.setPrices(prices);
    }
    
    return destination;

    } catch(Exception e) {
      throw new ConversionRuntimeException(e);
    }

  }

  private Set<ProductPriceDescription> getProductPriceDescriptions(ProductPrice price, List<com.salesmanager.shop.model.catalog.product.ProductPriceDescription> descriptions) throws ConversionException {
    if (CollectionUtils.isEmpty(descriptions)) {
      return Collections.emptySet();
    }
    Set<ProductPriceDescription> descs = new HashSet<ProductPriceDescription>();
    for(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc : descriptions) {
      ProductPriceDescription description = null;
      if(CollectionUtils.isNotEmpty(price.getDescriptions())) {
        for(ProductPriceDescription d : price.getDescriptions()) {
          if(isPositive(desc.getId()) && desc.getId().equals(d.getId())) {
            desc.setId(d.getId());
          }
        }
      }
      description = getDescription(desc);
      description.setProductPrice(price);
      descs.add(description);
    }
    return descs;
  }

  private String getRegion(PersistableInventory source) {
    return Optional.ofNullable(source.getRegion())
            .filter(StringUtils::isNotBlank)
            .orElse(Constants.ALL_REGIONS);
  }

  private ProductPriceDescription getDescription(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc) throws ConversionException {
    ProductPriceDescription target = new ProductPriceDescription();
    target.setDescription(desc.getDescription());
    target.setName(desc.getName());
    target.setTitle(desc.getTitle());
    target.setId(null);
    if(isPositive(desc.getId())) {
      target.setId(desc.getId());
    }
    Language lang = getLanguage(desc);
    target.setLanguage(lang);
    return target;

  }

  private Language getLanguage(com.salesmanager.shop.model.catalog.product.ProductPriceDescription desc) throws ConversionException {
    try {
      return Optional.ofNullable(languageService.getByCode(desc.getLanguage()))
              .orElseThrow(() -> new ConversionException("Language is null for code " + desc.getLanguage() + " use language ISO code [en, fr ...]"));
    } catch (ServiceException e) {
      throw new ConversionException(e);
    }
  }

}
