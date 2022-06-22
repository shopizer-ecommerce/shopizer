package com.salesmanager.shop.store.facade.product;

import static com.salesmanager.shop.util.ReadableEntityUtil.createReadableList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.business.services.catalog.product.variation.ProductVariationService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductInstanceMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductInstanceMapper;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ConstraintException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductCommonFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductInstanceFacade;


/**
 * Product instance management facade
 * @author carlsamson
 *
 */
@Component
public class ProductInstanceFacadeImpl implements ProductInstanceFacade {
	
	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper; 
	
	@Autowired
	private PersistableProductInstanceMapper persistableProductInstanceMapper; 
	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	
	@Autowired
	private ProductVariationService productVariationService;
	
	@Autowired
	private ProductFacade productFacade;
	
	@Autowired
	private ProductCommonFacade productCommonFacade;

	@Override
	public ReadableProductInstance get(Long instanceId, Long productId, MerchantStore store, Language language) {
		Optional<ProductInstance> productInstance =  this.getProductInstance(instanceId, productId, store);
		
		if(productInstance.isEmpty()) {
			throw new ResourceNotFoundException("Product instance + [" + instanceId + "] not found for store [" + store.getCode() + "]");
		}
		
		
		ProductInstance model =  productInstance.get();
		return readableProductInstanceMapper.convert(model, store, language);
		
		
	}

	@Override
	public boolean exists(String sku, MerchantStore store, Long productId, Language language) {
		ReadableProduct product = null;
		try {
			product = productCommonFacade.getProduct(store, productId, language);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Error while getting product [" + productId + "]",e);
		}

		return productInstanceService.exist(sku,product.getId());
	}

	@Override
	public Long create(PersistableProductInstance productInstance, Long productId, MerchantStore store,
			Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(productInstance, "ProductInstance cannot be null");
		Validate.notNull(productId, "Product id cannot be null");

		//variation and variation value should not be of same product option code
		if(
			productInstance.getVariant() != null && productInstance.getVariant().longValue() > 0 &&
			productInstance.getVariantValue() != null &&  productInstance.getVariantValue().longValue() > 0) 
		{

			List<ProductVariation> variations = productVariationService.getByIds(Arrays.asList(productInstance.getVariant(),productInstance.getVariantValue()), store);
			
			boolean differentOption = variations.stream().map(i -> i.getProductOption().getCode()).distinct().count() > 1;

			if(!differentOption) {
				throw new ConstraintException("Product option of instance.variant and instance.variantValue must be different");
			}

		}
		
		
		productInstance.setProductId(productId);
		productInstance.setId(null);
		ProductInstance instance = persistableProductInstanceMapper.convert(productInstance, store, language);
		
		try {
			productInstanceService.save(instance);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance for store [" + store.getCode() + "] and productId [" + productId + "]", e);
		}
		
		return instance.getId();
	}

	@Override
	public void update(Long instanceId, PersistableProductInstance productInstance, Long productId, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(productInstance, "ProductInstance cannot be null");
		Validate.notNull(productId, "Product id cannot be null");
		Validate.notNull(instanceId, "Product instance id cannot be null");
		
		Optional<ProductInstance> instanceModel = this.getProductInstance(instanceId, productId, store);
		if(instanceModel.isEmpty()) {
			throw new ResourceNotFoundException("ProductInstance with id [" + instanceId + "] not found for store [" + store.getCode() + "] and productId [" + productId + "]");
		}
		
		ProductInstance mergedModel = persistableProductInstanceMapper.merge(productInstance, instanceModel.get(), store, language);
		try {
			productInstanceService.save(mergedModel);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance for store [" + store.getCode() + "] and productId [" + productId + "]", e);
		}
		
	}
	
	private Optional<ProductInstance> getProductInstance(Long id, Long productId, MerchantStore store) {
		return productInstanceService.getById(id, productId, store);
	}


	@Override
	public void delete(Long productInstance, Long productId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(productInstance, "ProductInstance id cannot be null");
		Validate.notNull(productId, "Product id cannot be null");

		
		Optional<ProductInstance> instanceModel = this.getProductInstance(productInstance, productId, store);
		if(instanceModel.isEmpty()) {
			throw new ResourceNotFoundException("ProductInstance with id [" + productInstance + "] not found for store [" + store.getCode() + "] and productId [" + productId + "]");
		}
		
		try {
			productInstanceService.delete(instanceModel.get());
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot delete product instance [" + productInstance + "]  for store [" + store.getCode() + "] and productId [" + productId + "]", e);
		}

	}

	@Override
	public ReadableEntityList<ReadableProductInstance> list(Long productId, MerchantStore store, Language language,
			int page, int count) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(productId, "Product id cannot be null");
		
		Product product = productFacade.getProduct(productId, store);
		
		if(product == null) {
			throw new ResourceNotFoundException("Product with id [" + productId + "] not found for store [" + store.getCode() + "]");
		}
		
		Page<ProductInstance> instances = productInstanceService.getByProductId(store, product, language, page, count);
		
		
		List<ReadableProductInstance> readableInstances = instances.stream()
				.map(rp -> this.readableProductInstanceMapper.convert(rp, store, language)).collect(Collectors.toList());

	    
	    return createReadableList(instances, readableInstances);

	}
	

}
