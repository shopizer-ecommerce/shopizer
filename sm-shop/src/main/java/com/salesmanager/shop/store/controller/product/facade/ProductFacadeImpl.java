package com.salesmanager.shop.store.controller.product.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;
import com.salesmanager.shop.populator.catalog.PersistableProductPopulator;
import com.salesmanager.shop.populator.catalog.PersistableProductReviewPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductReviewPopulator;
import com.salesmanager.shop.populator.manufacturer.PersistableManufacturerPopulator;
import com.salesmanager.shop.populator.manufacturer.ReadableManufacturerPopulator;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

@Service("productFacade")
public class ProductFacadeImpl implements ProductFacade {
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private ManufacturerService manufacturerService;
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private ProductOptionService productOptionService;
	
	@Inject
	private ProductOptionValueService productOptionValueService;
	
	@Inject
	private TaxClassService taxClassService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ProductReviewService productReviewService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public PersistableProduct saveProduct(MerchantStore store, PersistableProduct product, Language language)
			throws Exception {
		

		com.salesmanager.shop.model.catalog.manufacturer.Manufacturer manufacturer = product.getManufacturer();
		
		if(manufacturer == null || (manufacturer.getId()==null || manufacturer.getId().longValue()==0)
				&& StringUtils.isBlank(manufacturer.getCode())) {
			
			//get default manufacturer
			Manufacturer defaultManufacturer = manufacturerService.getByCode(store, "DEFAULT");
			
			if(defaultManufacturer != null) {
			
				com.salesmanager.shop.model.catalog.manufacturer.Manufacturer m = new com.salesmanager.shop.model.catalog.manufacturer.Manufacturer();
				m.setId(defaultManufacturer.getId());
				m.setCode(defaultManufacturer.getCode());
				product.setManufacturer(m);
			
			}
			
		}
		
		PersistableProductPopulator persistableProductPopulator = new PersistableProductPopulator();
		
		persistableProductPopulator.setCategoryService(categoryService);
		persistableProductPopulator.setManufacturerService(manufacturerService);
		persistableProductPopulator.setLanguageService(languageService);
		persistableProductPopulator.setProductOptionService(productOptionService);
		persistableProductPopulator.setProductOptionValueService(productOptionValueService);
		persistableProductPopulator.setTaxClassService(taxClassService);
		persistableProductPopulator.setCustomerService(customerService);
		
		Product target = null;
		if(product.getId() != null && product.getId().longValue() > 0) {
			target = productService.getById(product.getId());
		} else {
			target = new Product();
		}
		 
		
		persistableProductPopulator.populate(product, target, store, language);
		
		productService.create(target);
		
		product.setId(target.getId());

		return product;
		

	}

	@Override
	public ReadableProduct getProduct(MerchantStore store, Long id, Language language)
			throws Exception {

		Product product = productService.getById(id);
		
		if(product==null) {
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
	public ReadableProduct getProduct(MerchantStore store, String sku,
			Language language) throws Exception {
		
		Product product = productService.getByCode(sku, language);
		
		if(product==null) {
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
	public ReadableProduct updateProductPrice(ReadableProduct product,
			ProductPriceEntity price, Language language) throws Exception {
		
		
		Product persistable = productService.getById(product.getId());
		
		if(persistable==null) {
			throw new Exception("product is null for id " + product.getId());
		}
		
		java.util.Set<ProductAvailability> availabilities = persistable.getAvailabilities();
		for(ProductAvailability availability : availabilities) {
			ProductPrice productPrice = availability.defaultPrice();
			productPrice.setProductPriceAmount(price.getOriginalPrice());
			if(price.isDiscounted()) {
				productPrice.setProductPriceSpecialAmount(price.getDiscountedPrice());
				if(!StringUtils.isBlank(price.getDiscountStartDate())) {
					Date startDate = DateUtil.getDate(price.getDiscountStartDate());
					productPrice.setProductPriceSpecialStartDate(startDate);
				}
				if(!StringUtils.isBlank(price.getDiscountEndDate())) {
					Date endDate = DateUtil.getDate(price.getDiscountEndDate());
					productPrice.setProductPriceSpecialEndDate(endDate);
				}
			}
			
		}
		
		productService.update(persistable);
		
		ReadableProduct readableProduct = new ReadableProduct();
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(persistable, readableProduct, persistable.getMerchantStore(), language);
		
		return readableProduct;
	}

	@Override
	public ReadableProduct updateProductQuantity(ReadableProduct product,
			int quantity, Language language) throws Exception {
		Product persistable = productService.getById(product.getId());
		
		if(persistable==null) {
			throw new Exception("product is null for id " + product.getId());
		}
		
		java.util.Set<ProductAvailability> availabilities = persistable.getAvailabilities();
		for(ProductAvailability availability : availabilities) {
			availability.setProductQuantity(quantity);
		}
		
		productService.update(persistable);
		
		ReadableProduct readableProduct = new ReadableProduct();
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(persistable, readableProduct, persistable.getMerchantStore(), language);
		
		return readableProduct;
	}

	@Override
	public void deleteProduct(Product product) throws Exception {
		productService.delete(product);
		
	}

	@Override
	public ReadableProductList getProductListsByCriterias(MerchantStore store, Language language,
			ProductCriteria criterias) throws Exception {

		
		Validate.notNull(criterias, "ProductCriteria must be set for this product");
		
		if(CollectionUtils.isNotEmpty(criterias.getCategoryIds())) {
			
			
			if(criterias.getCategoryIds().size()==1) {
				
				com.salesmanager.core.model.catalog.category.Category category = categoryService.getById(criterias.getCategoryIds().get(0));
				
				if(category != null) {
					String lineage = new StringBuilder().append(category.getLineage()).append(category.getId()).append("/").toString();
					
					List<com.salesmanager.core.model.catalog.category.Category> categories = categoryService.listByLineage(store, lineage);
				
					List<Long> ids = new ArrayList<Long>();
					if(categories!=null && categories.size()>0) {
						for(com.salesmanager.core.model.catalog.category.Category c : categories) {
							ids.add(c.getId());
						}
					} 
					ids.add(category.getId());
					criterias.setCategoryIds(ids);
				}
			}

			
		}
		
		com.salesmanager.core.model.catalog.product.ProductList products = productService.listByStore(store, language, criterias);

		
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
	public ReadableProduct addProductToCategory(Category category, Product product, Language language) throws Exception {
		
		Validate.notNull(category,"Category cannot be null");
		Validate.notNull(product,"Product cannot be null");
		
		product.getCategories().add(category);
		
		productService.update(product);
		
		ReadableProduct readableProduct = new ReadableProduct();
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, product.getMerchantStore(), language);
		
		return readableProduct;
		
		
	}

	@Override
	public ReadableProduct removeProductFromCategory(Category category, Product product, Language language)
			throws Exception {
		
		Validate.notNull(category,"Category cannot be null");
		Validate.notNull(product,"Product cannot be null");
		
		product.getCategories().remove(category);
		productService.update(product);	
		
		ReadableProduct readableProduct = new ReadableProduct();
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, product.getMerchantStore(), language);
		
		return readableProduct;
	}

	@Override
	public ReadableProduct getProductByCode(MerchantStore store, String uniqueCode, Language language)
			throws Exception {
		
		Product product = productService.getByCode(uniqueCode, language);
		
		
		ReadableProduct readableProduct = new ReadableProduct();
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, product.getMerchantStore(), language);
		
		return readableProduct;
	}

	@Override
	public void saveOrUpdateReview(PersistableProductReview review, MerchantStore store, Language language) throws Exception {
		PersistableProductReviewPopulator populator = new PersistableProductReviewPopulator();
		populator.setLanguageService(languageService);
		populator.setCustomerService(customerService);
		populator.setProductService(productService);
		
		com.salesmanager.core.model.catalog.product.review.ProductReview rev = new com.salesmanager.core.model.catalog.product.review.ProductReview();
		populator.populate(review, rev, store, language);
	
		if(review.getId()==null) {
			productReviewService.create(rev);
		} else {
			productReviewService.update(rev);
		}

		
		review.setId(rev.getId());

	}

	@Override
	public void deleteReview(ProductReview review, MerchantStore store, Language language) throws Exception {
		productReviewService.delete(review);
		
	}

	@Override
	public List<ReadableProductReview> getProductReviews(Product product, MerchantStore store, Language language)
			throws Exception {
		
		
		List<ProductReview> reviews = productReviewService.getByProduct(product);
		
		ReadableProductReviewPopulator populator = new ReadableProductReviewPopulator();
		
		List<ReadableProductReview> productReviews = new ArrayList<ReadableProductReview>();
		
		for(ProductReview review : reviews) {
			ReadableProductReview readableReview = new ReadableProductReview();
			populator.populate(review, readableReview, store, language);
			productReviews.add(readableReview);
		}
		
		
		
		return productReviews;
	}

	@Override
	public void saveOrUpdateManufacturer(PersistableManufacturer manufacturer, MerchantStore store, Language language)
			throws Exception {
		
		PersistableManufacturerPopulator populator = new PersistableManufacturerPopulator();
		populator.setLanguageService(languageService);

		
		Manufacturer manuf = new Manufacturer();
		populator.populate(manufacturer, manuf, store,  language);
		
		manufacturerService.saveOrUpdate(manuf);
		
		manufacturer.setId(manuf.getId());
		
	}

	@Override
	public void deleteManufacturer(Manufacturer manufacturer, MerchantStore store, Language language) throws Exception {
		manufacturerService.delete(manufacturer);
		
	}

	@Override
	public ReadableManufacturer getManufacturer(Long id, MerchantStore store, Language language) throws Exception {
		Manufacturer manufacturer = manufacturerService.getById(id);
		
		if(manufacturer==null) {
			return null;
		}
		
		ReadableManufacturer readableManufacturer = new ReadableManufacturer();
		
		ReadableManufacturerPopulator populator = new ReadableManufacturerPopulator();
		populator.populate(manufacturer, readableManufacturer, store, language);

		
		return readableManufacturer;
	}

	@Override
	public List<ReadableManufacturer> getAllManufacturers(MerchantStore store, Language language) throws Exception {
		
		
		List<Manufacturer> manufacturers = manufacturerService.listByStore(store);
		ReadableManufacturerPopulator populator = new ReadableManufacturerPopulator();
		List<ReadableManufacturer> returnList = new ArrayList<ReadableManufacturer>();
		
		for(Manufacturer m : manufacturers) {
			
			ReadableManufacturer readableManufacturer = new ReadableManufacturer();
			populator.populate(m, readableManufacturer, store, language);
			returnList.add(readableManufacturer);
		}

		return returnList;
	}

}
