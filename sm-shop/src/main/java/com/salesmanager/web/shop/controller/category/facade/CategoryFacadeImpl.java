package com.salesmanager.web.shop.controller.category.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.entity.catalog.category.PersistableCategory;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;
import com.salesmanager.web.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.web.populator.catalog.ReadableCategoryPopulator;


@Service( value = "categoryFacade" )
public class CategoryFacadeImpl implements CategoryFacade {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;

	@Override
	public List<ReadableCategory> getCategoryHierarchy(MerchantStore store,
			int depth, Language language) throws Exception {
		
		List<Category> categories = categoryService.listByDepth(store, 2, language);
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
		
		Category dbCategory = populator.populate(category, new Category(), store, store.getDefaultLanguage());
		
		this.saveCategory(store, dbCategory, null);
		
		
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

}
