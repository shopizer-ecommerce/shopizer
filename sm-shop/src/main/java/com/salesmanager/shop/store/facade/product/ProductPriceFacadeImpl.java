package com.salesmanager.shop.store.facade.product;

import static com.salesmanager.core.business.utils.NumberUtils.isPositive;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.catalog.product.price.ProductPriceService;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.inventory.PersistableProductPriceMapper;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.populator.catalog.ReadableProductPricePopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductPriceFacade;

@Service
public class ProductPriceFacadeImpl implements ProductPriceFacade {
	
	
	@Autowired
	private ProductPriceService productPriceService;
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private ProductAvailabilityService productAvailabilityService;
	
	
	@Autowired
	private PersistableProductPriceMapper persistableProductPriceMapper;

	@Override
	public Long save(PersistableProductPrice price, MerchantStore store) {
		
		
		ProductPrice productPrice = persistableProductPriceMapper.convert(price, store, store.getDefaultLanguage());
		try {
			if(!isPositive(productPrice.getId())) {
				//avoid detached entity failed to persist
				productPrice.getProductAvailability().setPrices(null);
				productPrice = productPriceService
						.saveOrUpdate(productPrice);
			} else {
				productPrice = productPriceService
						.saveOrUpdate(productPrice);
			}

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while creating a ProductPrice");
		}
		return productPrice.getId();
	}


	@Override
	public List<ReadableProductPrice> list(String sku, Long inventoryId, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(sku, "Product sku cannot be null");
		Validate.notNull(inventoryId, "Product inventory cannot be null");
		
		List<ProductPrice> prices = productPriceService.findByInventoryId(inventoryId, sku, store);
		List<ReadableProductPrice> returnPrices = prices.stream().map(p -> {
			try {
				return this.readablePrice(p, store, language);
			} catch (ConversionException e) {
				throw new ServiceRuntimeException("An exception occured while getting product price for sku [" + sku + "] and Store [" + store.getCode() + "]", e);
			}
		}).collect(Collectors.toList());
		
		return returnPrices;
		
		
	}

	@Override
	public List<ReadableProductPrice> list(String sku, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(sku, "Product sku cannot be null");

			List<ProductPrice> prices = productPriceService.findByProductSku(sku, store);
			List<ReadableProductPrice> returnPrices = prices.stream().map(p -> {
				try {
					return this.readablePrice(p, store, language);
				} catch (ConversionException e) {
					throw new ServiceRuntimeException("An exception occured while getting product price for sku [" + sku + "] and Store [" + store.getCode() + "]", e);
				}
			}).collect(Collectors.toList());
			
			return returnPrices;

	}


	@Override
	public void delete(Long priceId, String sku, MerchantStore store) {
		Validate.notNull(priceId, "Product Price id cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(sku, "Product sku cannot be null");
		ProductPrice productPrice = productPriceService.findById(priceId, sku, store);
		if(productPrice == null) {
			throw new ServiceRuntimeException("An exception occured while getting product price [" + priceId + "] for product sku [" + sku + "] and Store [" + store.getCode() + "]");
		}
		
		try {
			productPriceService.delete(productPrice);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while deleting product price [" + priceId + "] for product sku [" + sku + "] and Store [" + store.getCode() + "]", e);
		}
		
	}
	
	private ReadableProductPrice readablePrice (ProductPrice price, MerchantStore store, Language language) throws ConversionException {
		ReadableProductPricePopulator populator = new ReadableProductPricePopulator();
		populator.setPricingService(pricingService);
		return populator.populate(price, store, language);
	}


	@Override
	public ReadableProductPrice get(String sku, Long productPriceId, MerchantStore store, Language language) {
		Validate.notNull(productPriceId, "Product Price id cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(sku, "Product sku cannot be null");
		ProductPrice price = productPriceService.findById(productPriceId, sku, store);
		
		if(price == null) {
			throw new ResourceNotFoundException("ProductPrice with id [" + productPriceId + " not found for product sku [" + sku + "] and Store [" + store.getCode() + "]");
		}
		
		try {
			return readablePrice(price, store, language);
		} catch (ConversionException e) {
			throw new ServiceRuntimeException("An exception occured while deleting product price [" + productPriceId + "] for product sku [" + sku + "] and Store [" + store.getCode() + "]", e);
		}
	}

}
