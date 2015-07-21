package com.salesmanager.core.business.catalog.product.dao;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.model.ProductList;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;

public interface ProductDao extends SalesManagerEntityDao<Long, Product> {
	
	Product getProductForLocale(long productId, Language language, Locale locale);

	@SuppressWarnings("rawtypes")
	List<Product> getProductsForLocale(MerchantStore store, Set categoryIds, Language language,
			Locale locale);

	@SuppressWarnings("rawtypes")
	List<Product> getProductsListByCategories(Set categoryIds);


	/**
	 * Method to be used for getting a list of products in a given language based on one to many criteria
	 * @param store
	 * @param language
	 * @param criteria
	 * @return
	 */
	ProductList listByStore(MerchantStore store, Language language, ProductCriteria criteria);

	List<Product> listByStore(MerchantStore store);

	List<Product> listByTaxClass(TaxClass taxClass);

	List<Product> getProductsListByCategories(Set<Long> categoryIds,
			Language language);

	Product getBySeUrl(MerchantStore store, String seUrl, Locale locale);

	Product getByCode(String productCode, Language language);
	
}
