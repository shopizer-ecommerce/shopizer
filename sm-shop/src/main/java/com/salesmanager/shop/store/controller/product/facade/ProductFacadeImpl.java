package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.populator.catalog.PersistableProductPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

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
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public PersistableProduct saveProduct(MerchantStore store, PersistableProduct product, Language language)
			throws Exception {
		
		
		PersistableProductPopulator persistableProductPopulator = new PersistableProductPopulator();
		
		persistableProductPopulator.setCategoryService(categoryService);
		persistableProductPopulator.setManufacturerService(manufacturerService);
		persistableProductPopulator.setLanguageService(languageService);
		persistableProductPopulator.setProductOptionService(productOptionService);
		persistableProductPopulator.setProductOptionValueService(productOptionValueService);
		persistableProductPopulator.setTaxClassService(taxClassService);
		
		Product target = new Product();
		
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

}
