package com.salesmanager.web.shop.controller.category.facade;

import java.util.List;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.category.PersistableCategory;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;

public interface CategoryFacade {
	
	/**
	 * Returns a list of ReadableCategory ordered and built according to a given depth
	 * @param store
	 * @param depth
	 * @param language
	 * @return
	 * @throws Exception
	 */
	List<ReadableCategory> getCategoryHierarchy(MerchantStore store, int depth, Language language) throws Exception;
	
	void saveCategory(MerchantStore store, PersistableCategory category) throws Exception;

}
