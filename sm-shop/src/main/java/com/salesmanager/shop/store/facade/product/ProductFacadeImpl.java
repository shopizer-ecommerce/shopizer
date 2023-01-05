package com.salesmanager.shop.store.facade.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.product.PersistableProduct;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LocaleUtils;

@Service("productFacade")
@Profile({ "default", "cloud", "gcp", "aws", "mysql" , "local" })
public class ProductFacadeImpl implements ProductFacade {

	@Inject
	private CategoryService categoryService;
	
	@Inject
	private ProductAttributeService productAttributeService;

	@Inject
	private ProductService productService;

	@Inject
	private PricingService pricingService;

	@Inject
	private ProductRelationshipService productRelationshipService;


	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	public void updateProduct(MerchantStore store, PersistableProduct product, Language language) {

		Validate.notNull(product, "Product must not be null");
		Validate.notNull(product.getId(), "Product id must not be null");

		// get original product
		productService.getById(product.getId());

	}

	@Override
	public ReadableProduct getProduct(MerchantStore store, String sku, Language language) throws Exception {

		Product product = productService.getBySku(sku, store, language);

		if (product == null) {
			return null;
		}

		ReadableProduct readableProduct = new ReadableProduct();

		ReadableProductPopulator populator = new ReadableProductPopulator();

		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, store, language);

		return readableProduct;
	}

	@Override
	public ReadableProductList getProductListsByCriterias(MerchantStore store, Language language,
			ProductCriteria criterias) throws Exception {

		Validate.notNull(criterias, "ProductCriteria must be set for this product");

		/** This is for category **/
		if (CollectionUtils.isNotEmpty(criterias.getCategoryIds())) {

			if (criterias.getCategoryIds().size() == 1) {

				com.salesmanager.core.model.catalog.category.Category category = categoryService
						.getById(criterias.getCategoryIds().get(0));

				if (category != null) {
					String lineage = new StringBuilder().append(category.getLineage())
							.toString();

					List<com.salesmanager.core.model.catalog.category.Category> categories = categoryService
							.getListByLineage(store, lineage);

					List<Long> ids = new ArrayList<Long>();
					if (categories != null && categories.size() > 0) {
						for (com.salesmanager.core.model.catalog.category.Category c : categories) {
							ids.add(c.getId());
						}
					}
					ids.add(category.getId());
					criterias.setCategoryIds(ids);
				}
			}
		}

		
		Page<Product> modelProductList = productService.listByStore(store, language, criterias, criterias.getStartPage(), criterias.getMaxCount());
		
		List<Product> products = modelProductList.getContent();
		
		List<Product> prds = products.stream().sorted(Comparator.comparing(Product::getSortOrder)).collect(Collectors.toList());
		products = prds;
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);

		ReadableProductList productList = new ReadableProductList();
		for (Product product : products) {

			// create new proxy product
			ReadableProduct readProduct = populator.populate(product, new ReadableProduct(), store, language);
			productList.getProducts().add(readProduct);

		}

		// productList.setTotalPages(products.getTotalCount());
		productList.setRecordsTotal(modelProductList.getTotalElements());
		productList.setNumber(productList.getProducts().size());

		productList.setTotalPages(modelProductList.getTotalPages());

		return productList;
	}
	
	@Override
	public ReadableProduct getProductByCode(MerchantStore store, String uniqueCode, Language language) {

		Product product = null;
		try {
			product = productService.getBySku(uniqueCode, store, language);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}

		ReadableProduct readableProduct = new ReadableProduct();

		ReadableProductPopulator populator = new ReadableProductPopulator();

		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		try {
			populator.populate(product, readableProduct, product.getMerchantStore(), language);
		} catch (ConversionException e) {
			throw new ConversionRuntimeException("Product with code [" + uniqueCode + "] cannot be converted", e);
		}

		return readableProduct;
	}

	@Override
	public List<ReadableProduct> relatedItems(MerchantStore store, Product product, Language language)
			throws Exception {
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);

		List<ProductRelationship> relatedItems = productRelationshipService.getByType(store, product,
				ProductRelationshipType.RELATED_ITEM);
		if (relatedItems != null && relatedItems.size() > 0) {
			List<ReadableProduct> items = new ArrayList<ReadableProduct>();
			for (ProductRelationship relationship : relatedItems) {
				Product relatedProduct = relationship.getRelatedProduct();
				ReadableProduct proxyProduct = populator.populate(relatedProduct, new ReadableProduct(), store,
						language);
				items.add(proxyProduct);
			}
			return items;
		}
		return null;
	}



	@Override
	public ReadableProduct getProductBySeUrl(MerchantStore store, String friendlyUrl, Language language) throws Exception {

		Product product = productService.getBySeUrl(store, friendlyUrl, LocaleUtils.getLocale(language));

		if (product == null) {
			return null;
		}

		ReadableProduct readableProduct = new ReadableProduct();

		ReadableProductPopulator populator = new ReadableProductPopulator();

		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, store, language);

		return readableProduct;
	}

	/**
	@Override
	public ReadableProductPrice getProductPrice(Long id, ProductPriceRequest priceRequest, MerchantStore store, Language language) {
		Validate.notNull(id, "Product id cannot be null");
		Validate.notNull(priceRequest, "Product price request cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		try {
			Product model = productService.findOne(id, store);
			
			//TODO check if null
			List<Long> attrinutesIds = priceRequest.getOptions().stream().map(p -> p.getId()).collect(Collectors.toList());
			
			List<ProductAttribute> attributes = productAttributeService.getByAttributeIds(store, model, attrinutesIds);      
			
			for(ProductAttribute attribute : attributes) {
				if(attribute.getProduct().getId().longValue()!= id.longValue()) {
					//throw unauthorized
					throw new OperationNotAllowedException("Attribute with id [" + attribute.getId() + "] is not attached to product id [" + id + "]");
				}
			}
			
			FinalPrice price;
		
			price = pricingService.calculateProductPrice(model, attributes);
	    	ReadableProductPrice readablePrice = new ReadableProductPrice();
	    	ReadableFinalPricePopulator populator = new ReadableFinalPricePopulator();
	    	populator.setPricingService(pricingService);
	    	
	    	
	    	return populator.populate(price, readablePrice, store, language);
    	
		} catch (Exception e) {
			throw new ServiceRuntimeException("An error occured while getting product price",e);
		}

	}
	**/

	@Override
	public Product getProduct(Long id, MerchantStore store) {
		return productService.findOne(id, store);
	}



}
