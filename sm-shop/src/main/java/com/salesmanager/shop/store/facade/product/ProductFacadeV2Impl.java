package com.salesmanager.shop.store.facade.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.ReadableProductMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductInstanceMapper;
import com.salesmanager.shop.model.catalog.product.ProductPriceRequest;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.populator.catalog.ReadableFinalPricePopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LocaleUtils;


@Service("productFacadeV2")
@Profile({ "default", "cloud", "gcp", "aws", "mysql" , "local" })
public class ProductFacadeV2Impl implements ProductFacade {
	

	@Autowired
	private ProductService productService;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private ProductRelationshipService productRelationshipService;
	
	@Autowired
	private ReadableProductMapper readableProductMapper;
	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper;
	
	@Autowired
	private ProductAvailabilityService productAvailabilityService;
	
	@Autowired
	private ProductAttributeService productAttributeService;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public Product getProduct(String sku, MerchantStore store) {
		// same as v1
		return productService.getByCode(sku, store.getDefaultLanguage());
	}

	@Override
	public Product getProduct(Long id, MerchantStore store) {
		//same as v1
		return productService.findOne(id, store);
	}

	@Override
	public ReadableProduct getProductByCode(MerchantStore store, String uniqueCode, Language language) {

		
		Product product = productService.getBySku(uniqueCode, store, language);

		if (product == null) {
			throw new ResourceNotFoundException("Product [" + uniqueCode + "] not found for merchant [" + store.getCode() + "]");
		}
		
		if (product.getMerchantStore().getId() != store.getId()) {
			throw new ResourceNotFoundException("Product [" + uniqueCode + "] not found for merchant [" + store.getCode() + "]");
		}
		

		ReadableProduct readableProduct = readableProductMapper.convert(product, store, language);

		return readableProduct;
		
	}
	
	private ReadableProductInstance productInstance(ProductInstance instance, MerchantStore store, Language language) {
		
		return readableProductInstanceMapper.convert(instance, store, language);
		
	}

	@Override
	public ReadableProduct getProduct(MerchantStore store, String sku, Language language) throws Exception {
		return this.getProductByCode(store, sku, language);
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
		
		//the above get all possible images
		List<ReadableProductInstance> readableInstances = instances.stream().map(p -> this.productInstance(p, store, language)).collect(Collectors.toList());
		readableProduct.setVariants(readableInstances);
		
		return readableProduct;
		
	}


	@Override
	public ReadableProductList getProductListsByCriterias(MerchantStore store, Language language,
			ProductCriteria criterias) throws Exception {
		// same as v1
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
	public List<ReadableProduct> relatedItems(MerchantStore store, Product product, Language language)
			throws Exception {
		//same as v1
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
	public ReadableProductPrice getProductPrice(Long id, ProductPriceRequest priceRequest, MerchantStore store,
			Language language) {

		
		Validate.notNull(id, "Product id cannot be null");
		Validate.notNull(priceRequest, "Product price request cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		try {
			Product model = productService.findOne(id, store);
			
			List<ProductAttribute> attributes = null;
			
			if(!CollectionUtils.isEmpty(priceRequest.getOptions())) {
				List<Long> attrinutesIds = priceRequest.getOptions().stream().map(p -> p.getId()).collect(Collectors.toList());
				
				attributes = productAttributeService.getByAttributeIds(store, model, attrinutesIds);      
				
				for(ProductAttribute attribute : attributes) {
					if(attribute.getProduct().getId().longValue()!= id.longValue()) {
						//throw unauthorized
						throw new OperationNotAllowedException("Attribute with id [" + attribute.getId() + "] is not attached to product id [" + id + "]");
					}
				}
			}
			
			if(!StringUtils.isBlank(priceRequest.getSku())) {
				 //change default availability with sku (instance availability)
				List<ProductAvailability> availabilityList = productAvailabilityService.getBySku(priceRequest.getSku(), store);
				if(CollectionUtils.isNotEmpty(availabilityList)) {
					model.setAvailabilities(new HashSet<ProductAvailability>(availabilityList));
				}
			}
			
			FinalPrice price;
		
			//attributes can be null;
			price = pricingService.calculateProductPrice(model, attributes);
	    	ReadableProductPrice readablePrice = new ReadableProductPrice();
	    	ReadableFinalPricePopulator populator = new ReadableFinalPricePopulator();
	    	populator.setPricingService(pricingService);
	    	
	    	
	    	return populator.populate(price, readablePrice, store, language);
    	
		} catch (Exception e) {
			throw new ServiceRuntimeException("An error occured while getting product price",e);
		}
		

	}

}
