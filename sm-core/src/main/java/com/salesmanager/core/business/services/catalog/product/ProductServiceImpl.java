package com.salesmanager.core.business.services.catalog.product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.ProductRepository;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.business.services.catalog.product.price.ProductPriceService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.business.utils.CatalogServiceHelper;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

@Service("productService")
public class ProductServiceImpl extends SalesManagerEntityServiceImpl<Long, Product> implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	ProductRepository productRepository;
	
	@Inject
	CategoryService categoryService;
	
	@Inject
	ProductAvailabilityService productAvailabilityService;
	
	@Inject
	ProductPriceService productPriceService;

	@Inject
	ProductOptionService productOptionService;
	
	@Inject
	ProductOptionValueService productOptionValueService;
	
	@Inject
	ProductAttributeService productAttributeService;
	
	@Inject
	ProductRelationshipService productRelationshipService;
	
	@Inject
	SearchService searchService;
	
	@Inject
	ProductImageService productImageService;
	
	@Inject
	CoreConfiguration configuration;
	
	@Inject
	ProductReviewService productReviewService;
	
	@Inject
	public ProductServiceImpl(ProductRepository productRepository) {
		super(productRepository);
		this.productRepository = productRepository;
	}

	@Override
	public void addProductDescription(Product product, ProductDescription description)
			throws ServiceException {
		
		
		if(product.getDescriptions()==null) {
			product.setDescriptions(new HashSet<ProductDescription>());
		}
		
		product.getDescriptions().add(description);
		description.setProduct(product);
		update(product);
		searchService.index(product.getMerchantStore(), product);
	}
	
	@Override
	public List<Product> getProducts(List<Long> categoryIds) throws ServiceException {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set ids = new HashSet(categoryIds);
		return productRepository.getProductsListByCategories(ids);
		
	}
	
	public Product getById(Long productId) {
		return productRepository.getById(productId);
	}
	
	@Override
	public List<Product> getProducts(List<Long> categoryIds, Language language) throws ServiceException {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Long> ids = new HashSet(categoryIds);
		return productRepository.getProductsListByCategories(ids, language);
		
	}
	


	@Override
	public ProductDescription getProductDescription(Product product, Language language) {
		for (ProductDescription description : product.getDescriptions()) {
			if (description.getLanguage().equals(language)) {
				return description;
			}
		}
		return null;
	}
	
	@Override
	public Product getBySeUrl(MerchantStore store, String seUrl, Locale locale) {
		return productRepository.getByFriendlyUrl(store, seUrl, locale);
	}

	@Override
	public Product getProductForLocale(long productId, Language language, Locale locale)
			throws ServiceException {
		Product product =  productRepository.getProductForLocale(productId, language, locale);
		

		CatalogServiceHelper.setToAvailability(product, locale);
		CatalogServiceHelper.setToLanguage(product, language.getId());
		return product;
	}

	@Override
	public List<Product> getProductsForLocale(Category category,
			Language language, Locale locale) throws ServiceException {
		
		if(category==null) {
			throw new ServiceException("The category is null");
		}
		
		//Get the category list
		StringBuilder lineage = new StringBuilder().append(category.getLineage()).append(category.getId()).append("/");
		List<Category> categories = categoryService.listByLineage(category.getMerchantStore(),lineage.toString());
		Set<Long> categoryIds = new HashSet<Long>();
		for(Category c : categories) {
			
			categoryIds.add(c.getId());
			
		}
		
		categoryIds.add(category.getId());
		
		//Get products
		List<Product> products = productRepository.getProductsForLocale(category.getMerchantStore(), categoryIds, language, locale);
		
		//Filter availability
		
		return products;
	}
	
	@Override
	public ProductList listByStore(MerchantStore store,
			Language language, ProductCriteria criteria) {
		
		return productRepository.listByStore(store, language, criteria);
	}
	
	@Override
	public List<Product> listByStore(MerchantStore store) {
		
		return productRepository.listByStore(store);
	}
	
	@Override
	public List<Product> listByTaxClass(TaxClass taxClass) {
		return productRepository.listByTaxClass(taxClass);
	}
	
	@Override
	public Product getByCode(String productCode, Language language) {
		return productRepository.getByCode(productCode, language);
	}
		


	

	@Override
	public void delete(Product product) throws ServiceException {
		LOGGER.debug("Deleting product");
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(product.getMerchantStore(), "MerchantStore cannot be null in product");
		product = this.getById(product.getId());//Prevents detached entity error
		product.setCategories(null);
		
		Set<ProductImage> images = product.getImages();
		
		for(ProductImage image : images) {
			productImageService.removeProductImage(image);
		}
		
		product.setImages(null);
		
		//delete reviews
		List<ProductReview> reviews = productReviewService.getByProductNoCustomers(product);
		for(ProductReview review : reviews) {
			productReviewService.delete(review);
		}
		
		//related - featured
		List<ProductRelationship> relationships = productRelationshipService.listByProduct(product);
		for(ProductRelationship relationship : relationships) {
			productRelationshipService.delete(relationship);
		}
		
		super.delete(product);
		searchService.deleteIndex(product.getMerchantStore(), product);
		
	}
	
	@Override
	public void create(Product product) throws ServiceException {
		this.saveOrUpdate(product);
		searchService.index(product.getMerchantStore(), product);
	}
	
	@Override
	public void update(Product product) throws ServiceException {
		this.saveOrUpdate(product);
		searchService.index(product.getMerchantStore(), product);
	}
	

	private void saveOrUpdate(Product product) throws ServiceException {
		LOGGER.debug("Save or update product ");
		Validate.notNull(product,"product cannot be null");
		Validate.notNull(product.getAvailabilities(),"product must have at least one availability");
		Validate.notEmpty(product.getAvailabilities(),"product must have at least one availability");
		
		
		//List of original images
		Set<ProductImage> originalProductImages = null;
		
		if(product.getId()!=null && product.getId()>0) {
			originalProductImages = product.getImages();
		}
		
		/** save product first **/
		super.save(product);

		
		/**
		 * Image creation needs extra service to save the file in the CMS
		 */
		List<Long> newImageIds = new ArrayList<Long>();
		Set<ProductImage> images = product.getImages();
		
		try {
			
			if(images!=null && images.size()>0) {
				for(ProductImage image : images) {
					if(image.getImage()!=null && (image.getId()==null || image.getId()==0L)) {
						image.setProduct(product);
						
				        InputStream inputStream = image.getImage();
				        ImageContentFile cmsContentImage = new ImageContentFile();
				        cmsContentImage.setFileName( image.getProductImage() );
				        cmsContentImage.setFile( inputStream );
				        cmsContentImage.setFileContentType(FileContentType.PRODUCT);

						productImageService.addProductImage(product, image, cmsContentImage);
						newImageIds.add(image.getId());
					} else {
						productImageService.save(image);
						newImageIds.add(image.getId());
					}
				}
			}
			
			//cleanup old images
			if(originalProductImages!=null) {
				for(ProductImage image : originalProductImages) {
					if(!newImageIds.contains(image.getId())) {
						productImageService.delete(image);
					}
				}
			}
			
		} catch(Exception e) {
			LOGGER.error("Cannot save images " + e.getMessage());
		}
		


	}
	
	/*	@Override	
	public void saveOrUpdate(Product product) throws ServiceException {

		LOGGER.debug("Save or update product ");
		Validate.notNull(product,"product cannot be null");
		Validate.notNull(product.getAvailabilities(),"product must have at least one availability");
		Validate.notEmpty(product.getAvailabilities(),"product must have at least one availability");

		//List of original availabilities
		Set<ProductAvailability> originalAvailabilities = null;
		
		//List of original attributes
		Set<ProductAttribute> originalAttributes = null;
		
		//List of original reviews
		Set<ProductRelationship> originalRelationships = null;
		
		//List of original images
		Set<ProductImage> originalProductImages = null;
		
		
		if(product.getId()!=null && product.getId()>0) {
			LOGGER.debug("Update product",product.getId());
			//get original product
			Product originalProduct = this.getById(product.getId());
			originalAvailabilities = originalProduct.getAvailabilities();
			originalAttributes = originalProduct.getAttributes();
			originalRelationships = originalProduct.getRelationships();
			originalProductImages = originalProduct.getImages();
		} else {
			
			Set<ProductDescription> productDescriptions = product.getDescriptions();
			product.setDescriptions(null);
			
			super.create(product);
			
			for(ProductDescription productDescription : productDescriptions) {
				addProductDescription(product,productDescription);
			}
		}

		
		LOGGER.debug("Creating availabilities");
		
		//get availabilities
		Set<ProductAvailability> availabilities = product.getAvailabilities();
		List<Long> newAvailabilityIds = new ArrayList<Long>();
		if(availabilities!=null && availabilities.size()>0) {
			for(ProductAvailability availability : availabilities) {
				availability.setProduct(product);
				productAvailabilityService.saveOrUpdate(availability);
				newAvailabilityIds.add(availability.getId());
				//check prices
				Set<ProductPrice> prices = availability.getPrices();
				if(prices!=null && prices.size()>0) {

					for(ProductPrice price : prices) {
						price.setProductAvailability(availability);
						productPriceService.saveOrUpdate(price);
					}
				}	
			}
		}
		
		//cleanup old availability
		if(originalAvailabilities!=null) {
			for(ProductAvailability availability : originalAvailabilities) {
				if(!newAvailabilityIds.contains(availability.getId())) {
					productAvailabilityService.delete(availability);
				}
			}
		}
		
		LOGGER.debug("Creating attributes");
		List<Long> newAttributesIds = new ArrayList<Long>();
		if(product.getAttributes()!=null && product.getAttributes().size()>0) {
			Set<ProductAttribute> attributes = product.getAttributes();
			for(ProductAttribute attribute : attributes) {
				attribute.setProduct(product);
				ProductOption option = attribute.getProductOption();
				if(option!=null) {
					productOptionService.saveOrUpdate(option);
				}
				ProductOptionValue optionValue = attribute.getProductOptionValue();
				if(optionValue != null) {
					productOptionValueService.saveOrUpdate(optionValue);
				}

				productAttributeService.saveOrUpdate(attribute);
				newAttributesIds.add(attribute.getId());
			}
		}
		
		//cleanup old attributes
		if(originalAttributes!=null) {
			for(ProductAttribute attribute : originalAttributes) {
				if(!newAttributesIds.contains(attribute.getId())) {
					productAttributeService.delete(attribute);
				}
			}
		}
		
		
		LOGGER.debug("Creating relationships");
		List<Long> newRelationshipIds = new ArrayList<Long>();
		if(product.getRelationships()!=null && product.getRelationships().size()>0) {
			Set<ProductRelationship> relationships = product.getRelationships();
			for(ProductRelationship relationship : relationships) {
				relationship.setProduct(product);
				productRelationshipService.saveOrUpdate(relationship);
				newRelationshipIds.add(relationship.getId());
			}
		}
		//cleanup old relationships
		if(originalRelationships!=null) {
			for(ProductRelationship relationship : originalRelationships) {
				if(!newRelationshipIds.contains(relationship.getId())) {
					productRelationshipService.delete(relationship);
				}
			}
		}
		
		
		LOGGER.debug("Creating images");

		//get images
		List<Long> newImageIds = new ArrayList<Long>();
		Set<ProductImage> images = product.getImages();
		if(images!=null && images.size()>0) {
			for(ProductImage image : images) {
				if(image.getImage()!=null && (image.getId()==null || image.getId()==0L)) {
					image.setProduct(product);
					
			        InputStream inputStream = image.getImage();
			        ImageContentFile cmsContentImage = new ImageContentFile();
			        cmsContentImage.setFileName( image.getProductImage() );
			        cmsContentImage.setFile( inputStream );
			        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
					
					
					productImageService.addProductImage(product, image, cmsContentImage);
					newImageIds.add(image.getId());
				} else {
					productImageService.update(image);
					newImageIds.add(image.getId());
				}
			}
		}
		
		//cleanup old images
		if(originalProductImages!=null) {
			for(ProductImage image : originalProductImages) {
				if(!newImageIds.contains(image.getId())) {
					productImageService.delete(image);
				}
			}
		}
		
		if(product.getId()!=null && product.getId()>0) {
			super.update(product);
		}
		
		searchService.index(product.getMerchantStore(), product);

	}*/
	
	


	
}
