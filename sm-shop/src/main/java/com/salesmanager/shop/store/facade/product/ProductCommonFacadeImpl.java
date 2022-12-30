package com.salesmanager.shop.store.facade.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductMapper;
import com.salesmanager.shop.model.catalog.product.LightPersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;
import com.salesmanager.shop.model.catalog.product.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.product.ProductSpecification;
import com.salesmanager.shop.populator.catalog.PersistableProductReviewPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductReviewPopulator;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductCommonFacade;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;


/**
 * Version 1 Product management
 * Version 2 Recommends using productVariant
 * @author carlsamson
 *
 */
@Service("productCommonFacade")
public class ProductCommonFacadeImpl implements ProductCommonFacade {


	@Inject
	private LanguageService languageService;

	@Inject
	private ProductService productService;

	@Inject
	private PricingService pricingService;

	@Inject
	private CustomerService customerService;

	@Inject
	private ProductReviewService productReviewService;
	
	@Autowired
	private PersistableProductMapper persistableProductMapper;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public Long saveProduct(MerchantStore store, PersistableProduct product, Language language) {

		String manufacturer = Manufacturer.DEFAULT_MANUFACTURER;
		if (product.getProductSpecifications() != null) {
			manufacturer = product.getProductSpecifications().getManufacturer();
		} else {
			ProductSpecification specifications = new ProductSpecification();
			specifications.setManufacturer(manufacturer);
		}

		Product target = null;
		if (product.getId() != null && product.getId().longValue() > 0) {
			target = productService.getById(product.getId());
		} else {
			target = new Product();
		}

		try {
			
			target = persistableProductMapper.merge(product, target, store, language);
			target = productService.saveProduct(target);


			return target.getId();
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	public void updateProduct(MerchantStore store, PersistableProduct product, Language language) {

		Validate.notNull(product, "Product must not be null");
		Validate.notNull(product.getId(), "Product id must not be null");

		// get original product
		Product productModel = productService.getById(product.getId());


	}

	@Override
	public ReadableProduct getProduct(MerchantStore store, Long id, Language language) {

		Product product = productService.findOne(id, store);
		if (product == null) {
			throw new ResourceNotFoundException("Product [" + id + "] not found");
		}

		if (product.getMerchantStore().getId() != store.getId()) {
			throw new ResourceNotFoundException("Product [" + id + "] not found for store [" + store.getId() + "]");
		}

		ReadableProduct readableProduct = new ReadableProduct();
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		try {
			readableProduct = populator.populate(product, readableProduct, store, language);
		} catch (ConversionException e) {
			throw new ConversionRuntimeException("Error converting product [" + id + "]",e);
		}

		return readableProduct;
	}


	@Override
	public ReadableProduct updateProductPrice(ReadableProduct product, ProductPriceEntity price, Language language)
			throws Exception {

		Product persistable = productService.getById(product.getId());

		if (persistable == null) {
			throw new Exception("product is null for id " + product.getId());
		}

		java.util.Set<ProductAvailability> availabilities = persistable.getAvailabilities();
		for (ProductAvailability availability : availabilities) {
			ProductPrice productPrice = availability.defaultPrice();
			productPrice.setProductPriceAmount(price.getPrice());
			if (price.isDiscounted()) {
				productPrice.setProductPriceSpecialAmount(price.getDiscountedPrice());
				if (!StringUtils.isBlank(price.getDiscountStartDate())) {
					Date startDate = DateUtil.getDate(price.getDiscountStartDate());
					productPrice.setProductPriceSpecialStartDate(startDate);
				}
				if (!StringUtils.isBlank(price.getDiscountEndDate())) {
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
	public ReadableProduct updateProductQuantity(ReadableProduct product, int quantity, Language language)
			throws Exception {
		Product persistable = productService.getById(product.getId());

		if (persistable == null) {
			throw new Exception("product is null for id " + product.getId());
		}

		java.util.Set<ProductAvailability> availabilities = persistable.getAvailabilities();
		for (ProductAvailability availability : availabilities) {
			availability.setProductQuantity(quantity);
		}

		productService.saveProduct(persistable);

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
	public ReadableProduct addProductToCategory(Category category, Product product, Language language) {

		Validate.notNull(category, "Category cannot be null");
		Validate.notNull(product, "Product cannot be null");

		// not alloweed if category already attached
		List<Category> assigned = product.getCategories().stream()
				.filter(cat -> cat.getId().longValue() == category.getId().longValue()).collect(Collectors.toList());

		if (assigned.size() > 0) {
			throw new OperationNotAllowedException("Category with id [" + category.getId()
					+ "] already attached to product [" + product.getId() + "]");
		}

		product.getCategories().add(category);
		ReadableProduct readableProduct = new ReadableProduct();
		
		try {

			productService.saveProduct(product);
	
			ReadableProductPopulator populator = new ReadableProductPopulator();
	
			populator.setPricingService(pricingService);
			populator.setimageUtils(imageUtils);
			populator.populate(product, readableProduct, product.getMerchantStore(), language);
		
		} catch(Exception e) {
			throw new RuntimeException("Exception when adding product [" + product.getId() + "] to category [" + category.getId() + "]",e);
		}

		return readableProduct;

	}

	@Override
	public ReadableProduct removeProductFromCategory(Category category, Product product, Language language)
			throws Exception {

		Validate.notNull(category, "Category cannot be null");
		Validate.notNull(product, "Product cannot be null");

		product.getCategories().remove(category);
		productService.saveProduct(product);

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

		Product product = productService.getBySku(uniqueCode, store, language);

		ReadableProduct readableProduct = new ReadableProduct();

		ReadableProductPopulator populator = new ReadableProductPopulator();

		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);
		populator.populate(product, readableProduct, product.getMerchantStore(), language);

		return readableProduct;
	}

	@Override
	public void saveOrUpdateReview(PersistableProductReview review, MerchantStore store, Language language)
			throws Exception {
		PersistableProductReviewPopulator populator = new PersistableProductReviewPopulator();
		populator.setLanguageService(languageService);
		populator.setCustomerService(customerService);
		populator.setProductService(productService);

		com.salesmanager.core.model.catalog.product.review.ProductReview rev = new com.salesmanager.core.model.catalog.product.review.ProductReview();
		populator.populate(review, rev, store, language);

		if (review.getId() == null) {
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

		for (ProductReview review : reviews) {
			ReadableProductReview readableReview = new ReadableProductReview();
			populator.populate(review, readableReview, store, language);
			productReviews.add(readableReview);
		}

		return productReviews;
	}


	@Override
	public void update(Long productId, LightPersistableProduct product, MerchantStore merchant, Language language) {
		// Get product
		Product modified = productService.findOne(productId, merchant);

		// Update product with minimal set
		modified.setAvailable(product.isAvailable());

		for (ProductAvailability availability : modified.getAvailabilities()) {
			availability.setProductQuantity(product.getQuantity());
			if (!StringUtils.isBlank(product.getPrice())) {
				// set default price
				for (ProductPrice price : availability.getPrices()) {
					if (price.isDefaultPrice()) {
						try {
							price.setProductPriceAmount(pricingService.getAmount(product.getPrice()));
						} catch (ServiceException e) {
							throw new ServiceRuntimeException("Invalid product price format");
						}
					}
				}
			}
		}

		try {
			productService.save(modified);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot update product ", e);
		}

	}

	@Override
	public boolean exists(String sku, MerchantStore store) {

		return productService.exists(sku, store);
	}


	@Override
	public void deleteProduct(Long id, MerchantStore store) {

		Validate.notNull(id, "Product id cannot be null");
		Validate.notNull(store, "store cannot be null");

		Product p = productService.getById(id);

		if (p == null) {
			throw new ResourceNotFoundException("Product with id [" + id + " not found");
		}

		if (p.getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new ResourceNotFoundException(
					"Product with id [" + id + " not found for store [" + store.getCode() + "]");
		}

		try {
			productService.delete(p);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while deleting ptoduct with id [" + id + "]", e);
		}

	}



	@Override
	public Product getProduct(Long id, MerchantStore store) {
		return productService.findOne(id, store);
	}

	@Override
	public void update(String sku, LightPersistableProduct product, MerchantStore merchant, Language language) {
		// Get product
		Product modified = null;
		try {
			modified = productService.getBySku(sku, merchant, language);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}
		
		ProductVariant instance = modified.getVariants().stream()
				  .filter(inst -> sku.equals(inst.getSku()))
				  .findAny()
				  .orElse(null);
		
		if(instance!=null) {
			instance.setAvailable(product.isAvailable());
			
			for (ProductAvailability availability : instance.getAvailabilities()) {
				this.setAvailability(availability, product);
			}
		} else {
			// Update product with minimal set
			modified.setAvailable(product.isAvailable());
			
			for (ProductAvailability availability : modified.getAvailabilities()) {
				this.setAvailability(availability, product);
			}
		}

		try {
			productService.saveProduct(modified);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot update product ", e);
		}
		
	}
	
	/**
	 * edit availability
	 */
	private void setAvailability(ProductAvailability availability, LightPersistableProduct product) {
		availability.setProductQuantity(product.getQuantity());
		if (!StringUtils.isBlank(product.getPrice())) {
			// set default price
			for (ProductPrice price : availability.getPrices()) {
				if (price.isDefaultPrice()) {
					try {
						price.setProductPriceAmount(pricingService.getAmount(product.getPrice()));
					} catch (ServiceException e) {
						throw new ServiceRuntimeException("Invalid product price format");
					}
				}
			}
		}
	}


}
