package com.salesmanager.web.populator.catalog;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.review.ProductReview;
import com.salesmanager.core.business.catalog.product.model.review.ProductReviewDescription;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.product.PersistableProductReview;
import com.shopizer.search.utils.DateUtil;

public class PersistableProductReviewPopulator extends
		AbstractDataPopulator<PersistableProductReview, ProductReview> {
	
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LanguageService languageService;
	


	public LanguageService getLanguageService() {
		return languageService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	@Override
	public ProductReview populate(PersistableProductReview source,
			ProductReview target, MerchantStore store, Language language)
			throws ConversionException {
		
		
		Validate.notNull(customerService,"customerService cannot be null");
		Validate.notNull(productService,"productService cannot be null");
		Validate.notNull(languageService,"languageService cannot be null");
		
		try {
			
			if(target==null) {
				target = new ProductReview();
			}
			
			Customer customer = customerService.getById(source.getCustomerId());
			
			//check if customer belongs to store
			if(customer ==null || customer.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				throw new ConversionException("Invalid customer id for the given store");
			}
			
			target.setReviewDate(DateUtil.formatDate(source.getDate()));
			target.setCustomer(customer);
			target.setReviewRating(source.getRating());
			
			Product product = productService.getById(source.getProductId());
			
			//check if product belongs to store
			if(product ==null || product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				throw new ConversionException("Invalid product id for the given store");
			}
			
			target.setProduct(product);
			
			Language lang = languageService.getByCode(language.getCode());
			if(lang ==null) {
				throw new ConversionException("Invalid language code, use iso codes (en, fr ...)");
			}
			
			ProductReviewDescription description = new ProductReviewDescription();
			description.setDescription(source.getDescription());
			description.setLanguage(lang);
			description.setName("-");
			description.setProductReview(target);
			
			Set<ProductReviewDescription> descriptions = new HashSet<ProductReviewDescription>();
			descriptions.add(description);
			
			target.setDescriptions(descriptions);
			
			

			
			
			return target;
			
		} catch (Exception e) {
			throw new ConversionException("Cannot populate ProductReview", e);
		}
		
	}

	@Override
	protected ProductReview createTarget() {
		return null;
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


}
