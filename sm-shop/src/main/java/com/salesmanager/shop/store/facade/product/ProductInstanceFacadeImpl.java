package com.salesmanager.shop.store.facade.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductInstanceMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductInstanceMapper;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductInstanceFacade;

public class ProductInstanceFacadeImpl implements ProductInstanceFacade {
	
	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper; 
	
	@Autowired
	private PersistableProductInstanceMapper persistableProductInstanceMapper; 
	
	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	@Autowired
	private ProductFacade productFacade;

	@Override
	public ReadableProductInstance get(Long instanceId, Long productId, MerchantStore store, Language language) {
		Optional<ProductInstance> productInstance = productInstanceService.getById(instanceId, store);
		
		if(productInstance.isEmpty()) {
			throw new ResourceNotFoundException("Product instance + [" + instanceId + "] not found for store + [" + store.getCode() + "]");
		}
		
		
		ProductInstance model =  productInstance.get();
		return readableProductInstanceMapper.convert(model, store, language);
		
		
	}

	@Override
	public boolean exists(String sku, MerchantStore store, Long productId, Language language) {
		ReadableProduct product = null;
		try {
			product = productFacade.getProduct(store, productId, language);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Error while getting product [" + productId + "]",e);
		}

		return productInstanceService.exist(sku,product.getId());
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
	
	private Optional<ProductInstance> getProductInstance(Long id, MerchantStore store) {
		return productInstanceService.getById(id, store);
	}

	@Override
	public void delete(Long productInstance, MerchantStore store) {
		//productInstanceService.delete(null);

	}

	@Override
	public ReadableEntityList<ReadableProductInstance> list(Long ptoductId, MerchantStore store, Language language,
			int page, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
