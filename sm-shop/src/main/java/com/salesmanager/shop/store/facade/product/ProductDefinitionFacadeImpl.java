package com.salesmanager.shop.store.facade.product;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductDefinitionMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductDefinitionMapper;
import com.salesmanager.shop.model.catalog.product.product.definition.PersistableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductDefinitionFacade;
import com.salesmanager.shop.utils.ImageFilePath;

@Service("productDefinitionFacade")
@Profile({ "default", "cloud", "gcp", "aws", "mysql", "local" })
public class ProductDefinitionFacadeImpl implements ProductDefinitionFacade {
	


	@Inject
	private ProductService productService;


	@Autowired
	private PersistableProductDefinitionMapper persistableProductDefinitionMapper;
	
	@Autowired
	private ReadableProductDefinitionMapper readableProductDefinitionMapper;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public Long saveProductDefinition(MerchantStore store, PersistableProductDefinition product, Language language) {
		

		Product target = null;
		if (product.getId() != null && product.getId().longValue() > 0) {
			target = productService.getById(product.getId());
		} else {
			target = new Product();
		}

		try {
			target = persistableProductDefinitionMapper.merge(product, target, store, language);
			if (target.getId() != null && target.getId() > 0) {
				productService.update(target);
			} else {
				productService.createProduct(target);
				product.setId(target.getId());
			}

			return target.getId();
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}
		
	}

	@Override
	public void update(Long id, PersistableProductDefinition product, MerchantStore merchant,
			Language language) {
		product.setId(id);
		this.saveProductDefinition(merchant, product, language);
	}

	@Override
	public ReadableProductDefinition getProduct(MerchantStore store, Long id, Language language) {
		Product product = productService.findOne(id, store);
		return readableProductDefinitionMapper.convert(product, store, language);
	}

	@Override
	public ReadableProductDefinition getProductByCode(MerchantStore store, String uniqueCode, Language language) {
		
		Product product = productService.getByCode(uniqueCode, store);
		return readableProductDefinitionMapper.convert(product, store, language);

	}

}
