package com.salesmanager.web.populator.catalog;

import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.product.ReadableProductPrice;

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
	public ReadableProductPrice populate(ProductPrice source,
			ReadableProductPrice target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(pricingService,"pricingService must be set");
		Validate.notNull(source.getProductAvailability(),"productPrice.availability cannot be null");
		Validate.notNull(source.getProductAvailability().getProduct(),"productPrice.availability.product cannot be null");
		
		try {
			
			FinalPrice finalPrice = pricingService.calculateProductPrice(source.getProductAvailability().getProduct());
			
			target.setOriginalPrice(pricingService.getDisplayAmount(source.getProductPriceAmount(), store));
			if(finalPrice.isDiscounted()) {
				target.setDiscounted(true);
				target.setFinalPrice(pricingService.getDisplayAmount(source.getProductPriceSpecialAmount(), store));
			} else {
				target.setFinalPrice(pricingService.getDisplayAmount(finalPrice.getOriginalPrice(), store));
			}
			
		} catch(Exception e) {
			throw new ConversionException("Exception while converting to ReadableProductPrice",e);
		}
		
		
		
		return target;
	}

	@Override
	protected ReadableProductPrice createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
