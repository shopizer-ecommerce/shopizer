package com.salesmanager.shop.store.controller.items.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ProductItemsFacadeImpl implements ProductItemsFacade {
	
	
	@Inject
	ProductService productService;
	
	@Inject
	PricingService pricingService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private ProductRelationshipService productRelationshipService;

	@Override
	public ReadableProductList listItemsByManufacturer(MerchantStore store,
			Language language, Long manufacturerId, int startCount, int maxCount) throws Exception {
		
		
		ProductCriteria productCriteria = new ProductCriteria();
		productCriteria.setMaxCount(maxCount);
		productCriteria.setStartIndex(startCount);
		

		productCriteria.setManufacturerId(manufacturerId);
		com.salesmanager.core.model.catalog.product.ProductList products = productService.listByStore(store, language, productCriteria);

		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		
		
		ReadableProductList productList = new ReadableProductList();
		for(Product product : products.getProducts()) {

			//create new proxy product
			ReadableProduct readProduct = populator.populate(product, new ReadableProduct(), store, language);
			productList.getProducts().add(readProduct);
			
		}
		
		productList.setTotalCount(products.getTotalCount());
		
		
		return productList;
	}
	
	@Override
	public ReadableProductList listItemsByIds(MerchantStore store, Language language, List<Long> ids, int startCount,
			int maxCount) throws Exception {
		
		if(CollectionUtils.isEmpty(ids)) {
			return new ReadableProductList();
		}
		
		
		ProductCriteria productCriteria = new ProductCriteria();
		productCriteria.setMaxCount(maxCount);
		productCriteria.setStartIndex(startCount);
		productCriteria.setProductIds(ids);
		

		com.salesmanager.core.model.catalog.product.ProductList products = productService.listByStore(store, language, productCriteria);

		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		
		
		ReadableProductList productList = new ReadableProductList();
		for(Product product : products.getProducts()) {

			//create new proxy product
			ReadableProduct readProduct = populator.populate(product, new ReadableProduct(), store, language);
			productList.getProducts().add(readProduct);
			
		}
		
		productList.setTotalCount(products.getTotalCount());
		
		
		return productList;
	}

	@Override
	public ReadableProductList listItemsByGroup(String group, MerchantStore store, Language language) throws Exception {


		//get product group
		List<ProductRelationship> groups = productRelationshipService.getByGroup(store, group, language);

		if(group!=null) {
			List<Long> ids = new ArrayList<Long>();
			for(ProductRelationship relationship : groups) {
				Product product = relationship.getRelatedProduct();
				ids.add(product.getId());
			}
			
			ReadableProductList list = listItemsByIds(store, language, ids, 0, 0);
			return list;
		}
		
		return null;
	}

	@Override
	public ReadableProductList addItemToGroup(Product product, String group, MerchantStore store, Language language)
			throws Exception {
		
		Validate.notNull(product,"Product muust not be null");
		Validate.notNull(group,"group must not be null");
		
		ProductRelationship relationship = new ProductRelationship();
		relationship.setActive(true);
		relationship.setCode(group);
		relationship.setStore(store);
		relationship.setRelatedProduct(product);
		
		productRelationshipService.saveOrUpdate(relationship);
		
		
		return listItemsByGroup(group,store,language);
	}

	@Override
	public ReadableProductList removeItemFromGroup(Product product, String group, MerchantStore store,
			Language language) throws Exception {
		
		ProductRelationship relationship = null;
		List<ProductRelationship> relationships = productRelationshipService.getByGroup(store, group);
		
		for(ProductRelationship r : relationships) {
			if(r.getRelatedProduct().getId().longValue()==product.getId().longValue()) {
				relationship = r;
				break;
			}
		}
		
		productRelationshipService.delete(relationship);
		
		return listItemsByGroup(group,store,language);
	}

}
