package com.salesmanager.core.business.catalog.product.service;

import java.util.List;
import java.util.Locale;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.model.ProductList;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;



public interface ProductService extends SalesManagerEntityService<Long, Product> {

	void addProductDescription(Product product, ProductDescription description) throws ServiceException;
	
	ProductDescription getProductDescription(Product product, Language language);
	
	Product getProductForLocale(long productId, Language language, Locale locale) throws ServiceException;
	
	List<Product> getProductsForLocale(Category category, Language language, Locale locale) throws ServiceException;

	List<Product> getProducts(List<Long> categoryIds) throws ServiceException;



	ProductList listByStore(MerchantStore store, Language language,
			ProductCriteria criteria);



	void saveOrUpdate(Product product) throws ServiceException;

	List<Product> listByStore(MerchantStore store);

	List<Product> listByTaxClass(TaxClass taxClass);

	List<Product> getProducts(List<Long> categoryIds, Language language)
			throws ServiceException;

	Product getBySeUrl(MerchantStore store, String seUrl, Locale locale);

	/**
	 * Get a product by sku (code) field  and the language
	 * @param productCode
	 * @param language
	 * @return
	 */
	Product getByCode(String productCode, Language language);
	
}
	
