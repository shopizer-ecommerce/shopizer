package com.salesmanager.web.shop.controller.product.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionValueService;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.tax.service.TaxClassService;
import com.salesmanager.core.business.tax.service.TaxService;
import com.salesmanager.web.entity.catalog.product.PersistableProduct;
import com.salesmanager.web.populator.catalog.PersistableProductPopulator;

@Service("productFacade")
public class ProductFacadeImpl implements ProductFacade {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private ProductOptionService productOptionService;
	
	@Autowired
	private ProductOptionValueService productOptionValueService;
	
	@Autowired
	private TaxClassService taxClassService;
	
	@Autowired
	private ProductService productService;

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
		
		productService.saveOrUpdate(target);
		
		product.setId(target.getId());
		
		return product;
		

	}

}
