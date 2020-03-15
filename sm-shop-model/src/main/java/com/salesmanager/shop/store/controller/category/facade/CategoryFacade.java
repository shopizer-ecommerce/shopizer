package com.salesmanager.shop.store.controller.category.facade;

import java.util.List;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategoryList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariant;
import com.salesmanager.shop.model.entity.ListCriteria;

public interface CategoryFacade {


    /**
     * Returns a list of ReadableCategory ordered and built according to a given depth
     * @param store
     * @param depth
     * @param language
     * @param filter
     * @param page
     * @param count
     * @return ReadableCategoryList
     */
	ReadableCategoryList getCategoryHierarchy(MerchantStore store, ListCriteria criteria, int depth, Language language, List<String> filter, int page, int count);
	
	/**
	 * 
	 * @param store
	 * @param category
	 * @return PersistableCategory
	 */
	PersistableCategory saveCategory(MerchantStore store, PersistableCategory category);
	
	/**
	 * 
	 * @param store
	 * @param id
	 * @param language
	 * @return ReadableCategory
	 */
	ReadableCategory getById(MerchantStore store, Long id, Language language);
	
	/**
	 * 
	 * @param store
	 * @param code
	 * @param language
	 * @return ReadableCategory
	 * @throws Exception
	 */
	ReadableCategory getByCode(MerchantStore store, String code, Language language) throws Exception;

	Category getByCode(String code, MerchantStore store);
	
	void deleteCategory(Long categoryId, MerchantStore store);

	void deleteCategory(Category category);
	
	
	/**
	 * List product options variations for a given category
	 * @param categoryId
	 * @param store
	 * @param language
	 * @return
	 */
	List<ReadableProductVariant> categoryProductVariants(Long categoryId, MerchantStore store, Language language);

	/**
	 * Check if category code already exist
	 * @param store
	 * @param code
	 * @return
	 * @throws Exception
	 */
	boolean existByCode(MerchantStore store, String code);
	
	/**
	 * Move a Category from a node to another node
	 * @param child
	 * @param parent
	 * @param store
	 */
	void move(Long child, Long parent, MerchantStore store);
	
	/**
	 * Set category visible or not
	 * @param category
	 * @param store
	 */
	void setVisible(PersistableCategory category, MerchantStore store);
}
