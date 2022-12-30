package com.salesmanager.shop.mapper.catalog.product;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.variation.ProductVariationService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.variant.PersistableProductVariant;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class PersistableProductVariantMapper implements Mapper<PersistableProductVariant, ProductVariant> {
	
	@Autowired
	private ProductVariationService productVariationService;
	
	@Autowired
	private PersistableProductAvailabilityMapper persistableProductAvailabilityMapper;
	
	@Autowired
	private ProductService productService;

	@Override
	public ProductVariant convert(PersistableProductVariant source, MerchantStore store, Language language) {
		ProductVariant productVariantModel = new ProductVariant();
		return this.merge(source, productVariantModel, store, language);
	}

	@Override
	public ProductVariant merge(PersistableProductVariant source, ProductVariant destination, MerchantStore store,
			Language language) {
		
		//
		Long productVariation = source.getVariation();
		Long productVariationValue = source.getVariationValue();
		
		String productVariationCode = source.getVariationCode();
		String productVariationValueCode = source.getVariationValueCode();
		
		Optional<ProductVariation> variation = null;
		Optional<ProductVariation> variationValue = null;
		
		if(StringUtils.isEmpty(productVariationCode)) {
		
			variation = productVariationService.getById(store, productVariation);
			if(productVariationValue != null) {
				variationValue = productVariationService.getById(store, productVariationValue);
				if(variationValue.isEmpty()) {
					throw new ResourceNotFoundException("ProductVaritionValue [" + productVariationValue + "] + not found for store [" + store.getCode() + "]");
				}
				
			}
		} else {
			variation = productVariationService.getByCode(store, productVariationCode);
			if(productVariationValueCode != null) {
				variationValue = productVariationService.getByCode(store, productVariationValueCode);
				if(variationValue.isEmpty()) {
					throw new ResourceNotFoundException("ProductVaritionValue [" + productVariationValue + "] + not found for store [" + store.getCode() + "]");
				}
				
			}
		}
				
		
		if(variation.isEmpty()) {
			throw new ResourceNotFoundException("ProductVarition [" + productVariation + "] + not found for store [" + store.getCode() + "]");
		}
		
		destination.setVariation(variation.get());

		
		if(productVariationValue != null) {
			destination.setVariationValue(variationValue.get());
		}
		
		StringBuilder instanceCode = new StringBuilder();
		instanceCode.append(variation.get().getCode());
		if(productVariationValue != null && variationValue.get()!=null) {
			instanceCode.append(":").append(variationValue.get().getCode());
		}
		
		destination.setCode(instanceCode.toString());
		
		destination.setAvailable(source.isAvailable());
		destination.setDefaultSelection(source.isDefaultSelection());
		destination.setSku(source.getSku());
		
		if(StringUtils.isBlank(source.getDateAvailable())) {
			source.setDateAvailable(DateUtil.formatDate(new Date()));
		}
		
		if(source.getDateAvailable()!=null) {
			try {
				destination.setDateAvailable(DateUtil.getDate(source.getDateAvailable()));
			} catch (Exception e) {
				throw new ServiceRuntimeException("Cant format date [" + source.getDateAvailable() + "]");
			}
		}
		
		destination.setSortOrder(source.getSortOrder());
		
		
		/**
		 * Inventory
		 */
		if(source.getInventory() != null) {
			ProductAvailability availability = persistableProductAvailabilityMapper.convert(source.getInventory(), store, language);
			availability.setProductVariant(destination);
			destination.getAvailabilities().add(availability);
		}
		
		
		Product product = null;
				
		if(source.getProductId() != null && source.getProductId().longValue() > 0) {
			product = productService.findOne(source.getProductId(), store);
			
			if(product == null) {
				throw new ResourceNotFoundException("Product [" + source.getId() + "] + not found for store [" + store.getCode() + "]");
			}

			if(product.getMerchantStore().getId() != store.getId()) {
				throw new ResourceNotFoundException("Product [" + source.getId() + "] + not found for store [" + store.getCode() + "]");
			}
			
			if(product.getSku() != null && product.getSku().equals(source.getSku())) {
				throw new OperationNotAllowedException("Product variant sku [" + source.getSku() + "] + must be different than product instance sku [" + product.getSku() + "]");
			}
			
			destination.setProduct(product);
			
			
		}


		
		return destination;

	}

}
