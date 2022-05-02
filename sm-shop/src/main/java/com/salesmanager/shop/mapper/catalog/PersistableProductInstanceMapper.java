package com.salesmanager.shop.mapper.catalog;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.variation.ProductVariationService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.shopizer.search.utils.DateUtil;

@Component
public class PersistableProductInstanceMapper implements Mapper<PersistableProductInstance, ProductInstance> {
	
	@Autowired
	private ProductVariationService productVariationService;
	
	@Autowired
	private ProductService productService;

	@Override
	public ProductInstance convert(PersistableProductInstance source, MerchantStore store, Language language) {
		ProductInstance productInstanceModel = new ProductInstance();
		return this.merge(source, productInstanceModel, store, language);
	}

	@Override
	public ProductInstance merge(PersistableProductInstance source, ProductInstance destination, MerchantStore store,
			Language language) {
		
		//
		Long productVariant = source.getVariant();
		Long productVariantValue = source.getVariantValue();
		
		Optional<ProductVariation> variant = productVariationService.getById(store, productVariant);
		Optional<ProductVariation> variantValue = productVariationService.getById(store, productVariantValue);
		
		if(variant.isEmpty()) {
			throw new ResourceNotFoundException("ProductVariant [" + productVariant + "] + not found for store [" + store.getCode() + "]");
		}
		
		if(variant.isEmpty()) {
			throw new ResourceNotFoundException("ProductVariant [" + variantValue + "] + not found for store [" + store.getCode() + "]");
		}
		
		destination.setAvailable(source.isAvailable());
		destination.setDefaultSelection(source.isDefaultSelection());
		destination.setSku(source.getSku());
		if(source.getDateAvailable()!=null) {
			try {
				destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
			} catch (Exception e) {
				throw new ServiceRuntimeException("Cant format date [" + source.getDateAvailable() + "]");
			}
		}
		
		Product product = productService.getById(source.getId());
		if(product == null) {
			throw new ResourceNotFoundException("Product [" + source.getId() + "] + not found for store [" + store.getCode() + "]");
		}

		if(product.getMerchantStore().getId() != store.getId()) {
			throw new ResourceNotFoundException("Product [" + source.getId() + "] + not found for store [" + store.getCode() + "]");
		}
		
		destination.setProduct(product);
		
		return destination;

	}

}
