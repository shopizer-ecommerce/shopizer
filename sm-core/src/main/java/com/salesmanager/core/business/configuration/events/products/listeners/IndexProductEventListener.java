package com.salesmanager.core.business.configuration.events.products.listeners;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.configuration.events.products.DeleteProductAttributeEvent;
import com.salesmanager.core.business.configuration.events.products.DeleteProductEvent;
import com.salesmanager.core.business.configuration.events.products.DeleteProductImageEvent;
import com.salesmanager.core.business.configuration.events.products.DeleteProductVariantEvent;
import com.salesmanager.core.business.configuration.events.products.ProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductAttributeEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductImageEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductVariantEvent;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Index product in search module if it is configured to do so !
 * 
 * Should receive events that a product was created or updated or deleted
 * 
 * @author carlsamson
 *
 */
@Component
public class IndexProductEventListener implements ApplicationListener<ProductEvent> {

	@Autowired
	private SearchService searchService;

	@Autowired
	private ProductService productService;
	
    @Value("${search.noindex:false}")//skip indexing process
    private boolean noIndex;

	/**
	 * Listens to ProductEvent and ProductVariantEvent
	 */
	@Override
	public void onApplicationEvent(ProductEvent event) {
		
		
		if(!noIndex) {

			if (event instanceof SaveProductEvent) {
				saveProduct((SaveProductEvent) event);
			}
	
			if (event instanceof DeleteProductEvent) {
				deleteProduct((DeleteProductEvent) event);
			}
	
			if (event instanceof SaveProductVariantEvent) {
				saveProductVariant((SaveProductVariantEvent) event);
			}
	
			if (event instanceof DeleteProductVariantEvent) {
				deleteProductVariant((DeleteProductVariantEvent) event);
			}
			
			if (event instanceof SaveProductImageEvent) {
				saveProductImage((SaveProductImageEvent) event);
			}
	
			if (event instanceof DeleteProductImageEvent) {
				deleteProductImage((DeleteProductImageEvent) event);
			}
			
			if (event instanceof SaveProductAttributeEvent) {
				saveProductAttribute((SaveProductAttributeEvent) event);
			}
	
			if (event instanceof DeleteProductAttributeEvent) {
				deleteProductAttribute((DeleteProductAttributeEvent) event);
			}
			
			
		
		}

	}
	
	private Product productOfEvent(ProductEvent event) {
		
		Product product = event.getProduct();
		MerchantStore store = product.getMerchantStore();
		try {

			/**
			 * Refresh product
			 */

			Product fullProduct = productService.findOne(product.getId(), store);
			
			if(fullProduct != null) {
				product = fullProduct;
			} else {
				System.out.println("Product not loaded");
			}
			
		return product;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	void saveProduct(SaveProductEvent event) {
		
		try {
			Product product = productOfEvent(event);
			MerchantStore store = product.getMerchantStore();
	
			searchService.index(store, product);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	void deleteProduct(DeleteProductEvent event) {

		Product product = event.getProduct();
		MerchantStore store = product.getMerchantStore();

		try {
			searchService.deleteDocument(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
	}

	void saveProductVariant(SaveProductVariantEvent event) {

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();

		ProductVariant variant = event.getVariant();// to be removed

		/**
		 * add new variant to be saved
		 **/

		List<ProductVariant> filteredVariants = product.getVariants().stream()
				.filter(i -> variant.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		filteredVariants.add(variant);

		Set<ProductVariant> allVariants = new HashSet<ProductVariant>(filteredVariants);
		product.setVariants(allVariants);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}

	void deleteProductVariant(DeleteProductVariantEvent event) {

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();

		ProductVariant variant = event.getVariant();// to be removed

		/**
		 * remove variant to be saved
		 **/

		List<ProductVariant> filteredVariants = product.getVariants().stream()
				.filter(i -> variant.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		Set<ProductVariant> allVariants = new HashSet<ProductVariant>(filteredVariants);
		product.setVariants(allVariants);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}
	

	void saveProductImage(SaveProductImageEvent event) {

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();


		ProductImage image = event.getProductImage();// to be removed

		/**
		 * add new image to be saved
		 **/

		List<ProductImage> filteredImages = product.getImages().stream()
				.filter(i -> i.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		filteredImages.add(image);

		Set<ProductImage> allInmages = new HashSet<ProductImage>(filteredImages);
		product.setImages(allInmages);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}
	
	void deleteProductImage(DeleteProductImageEvent event) {
		
		//Product will be updated anyway so there is no need to reindex following an image removal
		return;
		
		/**

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();

		List<ProductImage> filteredImages = product.getImages().stream()
				.filter(i -> i.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());


		Set<ProductImage> allImages = new HashSet<ProductImage>(filteredImages);
		product.setImages(allImages);

		try {
			

			//searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		**/

	}
	
	void saveProductAttribute(SaveProductAttributeEvent event) {

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();

		ProductAttribute attribute = event.getProductAttribute();// to be removed

		/**
		 * add new attribute to be saved
		 **/

		List<ProductAttribute> filteredAttributes = product.getAttributes().stream()
				.filter(i -> i.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		filteredAttributes.add(attribute);

		Set<ProductAttribute> allAttributes = new HashSet<ProductAttribute>(filteredAttributes);
		product.setAttributes(allAttributes);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}
	
	void deleteProductAttribute(DeleteProductAttributeEvent event) {

		Product product = productOfEvent(event);

		MerchantStore store = product.getMerchantStore();

		/**
		 * add new attribute to be saved
		 **/

		List<ProductAttribute> filteredAttributes = product.getAttributes().stream()
				.filter(i -> i.getId().longValue() != i.getId().longValue()).collect(Collectors.toList());

		Set<ProductAttribute> allAttributes = new HashSet<ProductAttribute>(filteredAttributes);
		product.setAttributes(allAttributes);

		try {
			searchService.index(store, product);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Get document by product id and document exist if event is Product delete
	 * document create document if event is Variant get document get variants
	 * replace variant
	 */

}
