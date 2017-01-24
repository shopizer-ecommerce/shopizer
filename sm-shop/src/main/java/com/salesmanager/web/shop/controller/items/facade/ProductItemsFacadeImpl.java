package com.salesmanager.web.shop.controller.items.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;
import com.salesmanager.web.entity.catalog.product.ReadableProductList;
import com.salesmanager.web.populator.catalog.ReadableProductPopulator;
import com.salesmanager.web.utils.ImageFilePath;

@Component
public class ProductItemsFacadeImpl implements ProductItemsFacade {
	
	
	@Inject
	ProductService productService;
	
	@Inject
	PricingService pricingService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableProductList listItemsByManufacturer(MerchantStore store,
			Language language, Long manufacturerId, int startCount, int maxCount) throws Exception {
		
		
		ProductCriteria productCriteria = new ProductCriteria();
		productCriteria.setMaxCount(maxCount);
		productCriteria.setStartIndex(startCount);
		

		productCriteria.setManufacturerId(manufacturerId);
		com.salesmanager.core.business.catalog.product.model.ProductList products = productService.listByStore(store, language, productCriteria);

		
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
		
		
		ProductCriteria productCriteria = new ProductCriteria();
		productCriteria.setMaxCount(maxCount);
		productCriteria.setStartIndex(startCount);
		productCriteria.setProductIds(ids);
		

		com.salesmanager.core.business.catalog.product.model.ProductList products = productService.listByStore(store, language, productCriteria);

		
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

}
