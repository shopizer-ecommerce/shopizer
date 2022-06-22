package com.salesmanager.core.business.services.catalog.product;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;



public interface ProductService extends SalesManagerEntityService<Long, Product> {

	Optional<Product> retrieveById(Long id);

	void addProductDescription(Product product, ProductDescription description) throws ServiceException;

	ProductDescription getProductDescription(Product product, Language language);

	Product getProductForLocale(long productId, Language language, Locale locale) throws ServiceException;

	List<Product> getProductsForLocale(Category category, Language language, Locale locale) throws ServiceException;

	List<Product> getProducts(List<Long> categoryIds) throws ServiceException;

	List<Product> getProductsByIds(List<Long> productIds) throws ServiceException;
	
	Product createProduct(Product product) throws ServiceException;

	/**
	 * Get a product with only MerchantStore object
	 * @param productId
	 * @return
	 */
	Product getProductWithOnlyMerchantStoreById(Long productId);

	ProductList listByStore(MerchantStore store, Language language,
			ProductCriteria criteria);
	
	
	/**
	 * List using Page interface in order to unify all page requests (since 2.16.0) 
	 * @param store
	 * @param language
	 * @param criteria
	 * @param page
	 * @param count
	 * @return
	 */
	Page<Product> listByStore(MerchantStore store, Language language,
			ProductCriteria criteria, int page, int count);

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
	
	Product getByCode(String productCode, MerchantStore merchant);
	
	/**
	 * Product and or product instance
	 * @param productCode
	 * @param merchant
	 * @return
	 */
	Product getBySku(String productCode, MerchantStore merchant, Language language);

	/**
	 * Find a product for a specific merchant
	 * @param id
	 * @param merchant
	 * @return
	 */
	Product findOne(Long id, MerchantStore merchant);


}

