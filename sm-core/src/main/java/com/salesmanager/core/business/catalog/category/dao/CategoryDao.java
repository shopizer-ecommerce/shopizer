package com.salesmanager.core.business.catalog.category.dao;

import java.util.List;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface CategoryDao extends SalesManagerEntityDao<Long, Category> {

	List<Category> listBySeUrl(MerchantStore store, String seUrl);

	List<Category> listByStoreAndParent(MerchantStore store, Category category);

	List<Category> listByLineage(MerchantStore store, String lineage);

	List<Category> getByName(MerchantStore store, String name, Language language);

	Category getByCode(MerchantStore store, String code);
	
	List<Category> listByStore(MerchantStore store);

	List<Category> listByStore(MerchantStore store, Language language);

	Category getById(Long id);

	List<Category> listByDepth(MerchantStore store, int depth);

	List<Category> listByDepth(MerchantStore store, int depth, Language language);

	List<Category> listByLineage(String merchantStoreCode, String lineage);

	Category getByCode(String merchantStoreCode, String code);

	Category getBySeUrl(MerchantStore store, String seUrl);

	List<Category> listByParent(Category category, Language language);

	Category getByLanguage(long categoryId, Language language);

	List<Object[]> countProductsByCategories(MerchantStore store,
			List<Long> categoryIds);

	List<Category> getByCodes(MerchantStore store, List<String> codes,
			Language language);

	List<Category> getByIds(MerchantStore store, List<Long> ids,
			Language language);
	

}
