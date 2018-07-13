package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import org.apache.commons.lang.Validate;


public class ReadableFinalPricePopulator extends
    AbstractDataPopulator<FinalPrice, ReadableProductPrice> {

  private PricingService pricingService;

  public PricingService getPricingService() {
    return pricingService;
  }

  public void setPricingService(PricingService pricingService) {
    this.pricingService = pricingService;
  }

  @Override
  public ReadableProductPrice populate(FinalPrice source,
      ReadableProductPrice target, MerchantStore store, Language language) throws ServiceException {

    Validate.notNull(pricingService, "pricingService must be set");

    target.setOriginalPrice(pricingService.getDisplayAmount(source.getOriginalPrice(), store));
    if (source.isDiscounted()) {
      target.setDiscounted(true);
      target.setFinalPrice(pricingService.getDisplayAmount(source.getDiscountedPrice(), store));
    } else {
      target.setFinalPrice(pricingService.getDisplayAmount(source.getFinalPrice(), store));
    }

    return target;
  }

  @Override
  protected ReadableProductPrice createTarget() {
    // TODO Auto-generated method stub
    return null;
  }
}
