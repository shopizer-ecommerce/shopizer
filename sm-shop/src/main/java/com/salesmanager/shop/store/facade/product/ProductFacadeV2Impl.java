package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.ReadableProductMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductInstanceMapper;
import com.salesmanager.shop.model.catalog.product.ProductPriceRequest;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.LocaleUtils;


@Service("productFacadeV2")
@Profile({ "default", "cloud", "gcp", "aws", "mysql" , "local" })
public class ProductFacadeV2Impl implements ProductFacade {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PricingService pricingService;

	@Autowired
	private ProductRelationshipService productRelationshipService;
	
	@Autowired
	private ReadableProductMapper readableProductMapper;
	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper; 
	


	@Override
	public Product getProduct(String sku, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProduct(Long id, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableProduct getProductByCode(MerchantStore store, String uniqueCode, Language language) {
		
		
		Product product = productService.getByCode(uniqueCode, language);

		if (product == null) {
			throw new ResourceNotFoundException("Product [" + uniqueCode + "] not found for merchant [" + store.getCode() + "]");
		}
		
		if (product.getMerchantStore().getId() != store.getId()) {
			throw new ResourceNotFoundException("Product [" + uniqueCode + "] not found for merchant [" + store.getCode() + "]");
		}
		
		ReadableProduct readableProduct = readableProductMapper.convert(product, store, language);

		//get all instances for this product group by option
		//limit to 15 searches
		List<ProductInstance> instances = productInstanceService.getByProductId(store, product, language);
		
		
		//the above get all possible images
		List<ReadableProductInstance> readableInstances = instances.stream().map(p -> this.productInstance(p, store, language)).collect(Collectors.toList());
		
		readableProduct.setVariants(readableInstances);
		

		return readableProduct;
		
		
		
	}
	
	private ReadableProductInstance productInstance(ProductInstance instance, MerchantStore store, Language language) {
		
		return readableProductInstanceMapper.convert(instance, store, language);
		
	}

	//TODO
	@Override
	public ReadableProduct getProduct(MerchantStore store, String sku, Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableProduct getProductBySeUrl(MerchantStore store, String friendlyUrl, Language language)
			throws Exception {
		
		Product product = productService.getBySeUrl(store, friendlyUrl, LocaleUtils.getLocale(language));

		if (product == null) {
			throw new ResourceNotFoundException("Product [" + friendlyUrl + "] not found for merchant [" + store.getCode() + "]");
		}
		
		ReadableProduct readableProduct = readableProductMapper.convert(product, store, language);

		//get all instances for this product group by option
		//limit to 15 searches
		List<ProductInstance> instances = productInstanceService.getByProductId(store, product, language);
		
		//get default instance from the list
		
		//set the sku
		
		//set variant values

		return null;
	}


	@Override
	public ReadableProductList getProductListsByCriterias(MerchantStore store, Language language,
			ProductCriteria criterias) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReadableProduct> relatedItems(MerchantStore store, Product product, Language language)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public ReadableProductPrice getProductPrice(Long id, ProductPriceRequest priceRequest, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
