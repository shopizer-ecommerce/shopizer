package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import org.apache.commons.lang.Validate;


public class ReadableProductPricePopulator extends
    AbstractDataPopulator<ProductPrice, ReadableProductPrice> {


  private PricingService pricingService;

  public PricingService getPricingService() {
    return pricingService;
  }

  public void setPricingService(PricingService pricingService) {
    this.pricingService = pricingService;
  }

  @Override
  public ReadableProductPrice populate(ProductPrice source, ReadableProductPrice target,
      MerchantStore store, Language language) throws ServiceException {

    Validate.notNull(pricingService, "pricingService must be set");
    Validate.notNull(source.getProductAvailability(), "productPrice.availability cannot be null");
    Validate.notNull(source.getProductAvailability().getProduct(),
        "productPrice.availability.product cannot be null");

    FinalPrice finalPrice = pricingService
        .calculateProductPrice(source.getProductAvailability().getProduct());

    target.setOriginalPrice(pricingService.getDisplayAmount(source.getProductPriceAmount(), store));
    if (finalPrice.isDiscounted()) {
      target.setDiscounted(true);
      target.setFinalPrice(
          pricingService.getDisplayAmount(source.getProductPriceSpecialAmount(), store));
    } else {
      target.setFinalPrice(pricingService.getDisplayAmount(finalPrice.getOriginalPrice(), store));
    }

    return target;
  }

  @Override
  protected ReadableProductPrice createTarget() {
    // TODO Auto-generated method stub
    return null;
  }
}
