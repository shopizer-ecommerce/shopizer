package com.salesmanager.shop.store.facade.items;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
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
import com.salesmanager.shop.model.catalog.product.group.ProductGroup;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.items.facade.ProductItemsFacade;
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
		
		productList.setTotalPages(products.getTotalCount());
		
		
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
		
		productList.setNumber(products.getTotalCount());
		productList.setRecordsTotal(new Long(products.getTotalCount()));

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
			List<ReadableProduct> prds = list.getProducts().stream().sorted(Comparator.comparing(ReadableProduct::getSortOrder)).collect(Collectors.toList());
			list.setProducts(prds);
			list.setTotalPages(1);//no paging
			return list;
		}
		
		return null;
	}

	@Override
	public ReadableProductList addItemToGroup(Product product, String group, MerchantStore store, Language language) {
		
		Validate.notNull(product,"Product must not be null");
		Validate.notNull(group,"group must not be null");
		
		
		//check if product is already in group
		List<ProductRelationship> existList = null;
		try {
			existList = productRelationshipService.getByGroup(store, group).stream()
			.filter(prod -> prod.getRelatedProduct() != null && (product.getId().longValue() == prod.getRelatedProduct().getId()))
			.collect(Collectors.toList());
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("ExceptionWhile getting product group [" + group + "]", e);
		}
		
		if(existList.size()>0) {
			throw new OperationNotAllowedException("Product with id [" + product.getId() + "] is already in the group");
		}
		
		
		ProductRelationship relationship = new ProductRelationship();
		relationship.setActive(true);
		relationship.setCode(group);
		relationship.setStore(store);
		relationship.setRelatedProduct(product);

		try {
			productRelationshipService.saveOrUpdate(relationship);
			return listItemsByGroup(group,store,language);
		} catch (Exception e) {
			throw new ServiceRuntimeException("ExceptionWhile getting product group [" + group + "]", e);
		}
		
		
		
		
	}

	@Override
	public ReadableProductList removeItemFromGroup(Product product, String group, MerchantStore store,
			Language language) throws Exception {
		
		ProductRelationship relationship = null;
		List<ProductRelationship> relationships = productRelationshipService.getByType(store, product, group);

		for(ProductRelationship r : relationships) {
			if(r.getRelatedProduct().getId().longValue()==product.getId().longValue()) {
				productRelationshipService.delete(relationship);
			}
		}

		return listItemsByGroup(group,store,language);
	}

	@Override
	public void deleteGroup(String group, MerchantStore store) {
		
		Validate.notNull(group, "Group cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		
		try {
			productRelationshipService.deleteGroup(store, group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannor delete product group",e);
		}
		
	}

	@Override
	public ProductGroup createProductGroup(ProductGroup group, MerchantStore store) {
		Validate.notNull(group,"ProductGroup cannot be null");
		Validate.notNull(group.getCode(),"ProductGroup code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		try {
			productRelationshipService.addGroup(store, group.getCode());
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannor delete product group",e);
		}
		return group;
	}

	@Override
	public void updateProductGroup(String code, ProductGroup group, MerchantStore store) {
		try {
			List<ProductRelationship>  items = productRelationshipService.getGroupDefinition(store, code);
			if(CollectionUtils.isEmpty(items)) {
				throw new ResourceNotFoundException("ProductGroup [" + code + "] not found");
			}
			
			if(group.isActive()) {
				productRelationshipService.activateGroup(store, code);
			} else {
				productRelationshipService.deactivateGroup(store, code);
			}
			
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while updating product [" + code + "]");
		}
		
	}

	@Override
	public List<ProductGroup> listProductGroups(MerchantStore store, Language language) {
		Validate.notNull(store,"MerchantStore cannot be null");
		
		List<ProductRelationship> relationships = productRelationshipService.getGroups(store);
		
		List<ProductGroup> groups = new ArrayList<ProductGroup>();
		
		for(ProductRelationship relationship : relationships) {
			
			if(!"FEATURED_ITEM".equals(relationship.getCode())) {//do not add featured items
				ProductGroup g = new ProductGroup();
				g.setActive(relationship.isActive());
				g.setCode(relationship.getCode());
				groups.add(g);
			
			}
			
		}
		
		return groups;
	}

}
