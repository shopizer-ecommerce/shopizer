package com.salesmanager.core.business.repositories.catalog.product;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

public interface ProductRepositoryCustom {


		ProductList listByStore(MerchantStore store, Language language,
			ProductCriteria criteria);

		Product getProductWithOnlyMerchantStoreById(Long productId);

		 Product getByFriendlyUrl(MerchantStore store,String seUrl, Locale locale);

		List<Product> getProductsListByCategories(@SuppressWarnings("rawtypes") Set categoryIds);

		List<Product> getProductsListByCategories(Set<Long> categoryIds,
				Language language);

		List<Product> getProductsListByIds(Set<Long> productIds);

		List<Product> listByTaxClass(TaxClass taxClass);

		List<Product> listByStore(MerchantStore store);

		Product getProductForLocale(long productId, Language language,
				Locale locale);

		Product getById(Long productId);
		Product getById(Long productId, MerchantStore merchant);

		Product getByCode(String productCode, Language language);

		List<Product> getProductsForLocale(MerchantStore store,
				Set<Long> categoryIds, Language language, Locale locale);

}
