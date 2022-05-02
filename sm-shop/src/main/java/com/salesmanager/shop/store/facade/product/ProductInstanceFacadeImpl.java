package com.salesmanager.shop.store.facade.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.controller.product.facade.ProductInstanceFacade;

public class ProductInstanceFacadeImpl implements ProductInstanceFacade {
	
	
	@Autowired
	private ProductInstanceService productInstanceService;

	@Override
	public ReadableProductInstance get(Long instanceId, Long productId, MerchantStore store, Language language) {
		Optional<ProductInstance> productInstance = productInstanceService.getById(instanceId, store);
		
		if(productInstance.isEmpty()) {
			throw new ResourceNotFoundException("Product instance + [" + instanceId + "] not found for store + [" + store.getCode() + "]");
		}
		
		
		ProductInstance model =  productInstance.get();
		return null;
		
		
	}

	@Override
	public boolean exists(String sku, MerchantStore store) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long create(PersistableProductInstance productInstance, Long productId, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long variationId, PersistableProductInstance instance, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long productInstance, MerchantStore store) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReadableEntityList<ReadableProductInstance> list(Long ptoductId, MerchantStore store, Language language,
			int page, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
