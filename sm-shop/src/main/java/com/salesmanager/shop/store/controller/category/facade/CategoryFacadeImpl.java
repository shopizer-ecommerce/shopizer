package com.salesmanager.shop.store.controller.category.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.shop.populator.catalog.ReadableCategoryPopulator;


@Service( value = "categoryFacade" )
public class CategoryFacadeImpl implements CategoryFacade {
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private LanguageService languageService;
	
	private final static String FEATURED_CATEGORY = "featured";

	@Override
	public List<ReadableCategory> getCategoryHierarchy(MerchantStore store,
			int depth, Language language, String filter) throws Exception {
		
		
		List<Category> categories = null;
		
		if(!StringUtils.isBlank(filter)) {
			//as of 2.2.0 only filter by featured is supported
			if(FEATURED_CATEGORY.equals(filter)) {
				categories = categoryService.listByDepthFilterByFeatured(store, depth, language);
			} else {
				categories = categoryService.listByDepth(store, depth, language);
			}
		} else {
			categories = categoryService.listByDepth(store, depth, language);
		}
		
		 
		List<ReadableCategory> returnValues = new ArrayList<ReadableCategory>();
		
		Map<Long, ReadableCategory> categoryMap = new ConcurrentHashMap<Long, ReadableCategory>();
		
		ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
		
		for(Category category : categories) {
			
			if(category.isVisible()) {
				ReadableCategory readableCategory = new ReadableCategory();
				categoryPopulator.populate(category, readableCategory, store, language);
				
				returnValues.add(readableCategory);
				categoryMap.put(category.getId(), readableCategory);
			}
		}
		
		for(ReadableCategory category : returnValues) {
			
			if(category.isVisible()) {
				if(category.getParent()!=null) {
				    ReadableCategory parentCategory = categoryMap.get(category.getParent().getId());
					if(parentCategory!=null) {
						parentCategory.getChildren().add(category);
					}
				}
			}
		}
		
		returnValues = new ArrayList<ReadableCategory>();
		for(Object obj : categoryMap.values()) {
			
			ReadableCategory readableCategory = (ReadableCategory)obj;
			if(readableCategory.getDepth()==0) {//only from root
				returnValues.add(readableCategory);
			}
		}
		
        Collections.sort(returnValues, new Comparator<ReadableCategory>() {
            @Override
            public int compare(final ReadableCategory firstCategory, final ReadableCategory secondCategory) {
                return firstCategory.getSortOrder() - secondCategory.getSortOrder();
            }
         } );
		
		return returnValues;
	}

	@Override
	public void saveCategory(MerchantStore store, PersistableCategory category)
			throws Exception {
		
		PersistableCategoryPopulator populator = new PersistableCategoryPopulator();
		populator.setCategoryService(categoryService);
		populator.setLanguageService(languageService);
		
		Category target = null;
		
		if(category.getId() != null && category.getId().longValue() > 0) {
			target = categoryService.getById(category.getId());
		} else {
			target = new Category();
		}
		
		Category dbCategory = populator.populate(category, target, store, store.getDefaultLanguage());
		
		this.saveCategory(store, dbCategory, null);
		
		//set category id
		category.setId(dbCategory.getId());
		
		
	}
	
	private void saveCategory(MerchantStore store, Category c, Category parent) throws ServiceException {
		
		
		/**
		c.children1
		
		  			children1.children1
		  			children1.children2
		  
          								children1.children2.children1			
		
		**/
		
		/** set lineage **/
		if(parent!=null) {
			c.setParent(c);
			
			String lineage = parent.getLineage();
			int depth = parent.getDepth();

			c.setDepth(depth+1);
			c.setLineage(new StringBuilder().append(lineage).append(parent.getId()).append("/").toString());
			
		}
		
		c.setMerchantStore(store);
		
		//remove children
		List<Category> children = c.getCategories();
		c.setCategories(null);
		
		/** set parent **/
		if(parent!=null) {
			c.setParent(parent);
		}
		
		categoryService.saveOrUpdate(c);
		
		
		if(!CollectionUtils.isEmpty(children)) {
			parent = c;
			for(Category sub : children) {
				
				this.saveCategory(store, sub, parent);
				
			}
		}
	}

	@Override
	public ReadableCategory getById(MerchantStore store, Long id, Language language) throws Exception {
		Category categoryModel = categoryService.getByLanguage(id, language);
		
		if(categoryModel == null)
			return null;
		
		StringBuilder lineage = new StringBuilder();
		lineage.append(categoryModel.getLineage());
		lineage.append(categoryModel.getId());
		
		//get children
		List<Category> children = categoryService.listByLineage(store, lineage.toString());
		
		

		ReadableCategoryPopulator populator = new ReadableCategoryPopulator();

		
		ReadableCategory category = populator.populate(categoryModel, new ReadableCategory(), store, language);

		Map<Long, ReadableCategory> categoryMap = new ConcurrentHashMap<Long, ReadableCategory>();
		List<ReadableCategory> returnValues = new ArrayList<ReadableCategory>();
		categoryMap.put(categoryModel.getId(), category);
		
		
		for(Category child : children) {
			ReadableCategory c = new ReadableCategory();
			populator.populate(child, c, store, language);
			returnValues.add(c);
			categoryMap.put(c.getId(),c);
		}
		
		//traverse map and add child to parent
		for(ReadableCategory readable : returnValues) {
			
			if(readable.getParent() != null) {
				
				ReadableCategory rc = categoryMap.get(readable.getParent().getId());
				rc.getChildren().add(readable);
				
			}
		}
		
		
		return category;

	}

	@Override
	public void deleteCategory(Category category) throws Exception {
		categoryService.delete(category);
	}

	@Override
	public ReadableCategory getByCode(MerchantStore store, String code, Language language) throws Exception {

		Validate.notNull(code,"category code must not be null");
		ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
		ReadableCategory readableCategory = new ReadableCategory();
		
		Category category = categoryService.getByCode(store, code);
		categoryPopulator.populate(category, readableCategory, store, language);
		
		return readableCategory;
		
	}

}
