package com.salesmanager.web.shop.controller.category.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;
import com.salesmanager.web.populator.catalog.ReadableCategoryPopulator;


@Service( value = "categoryFacade" )
public class CategoryFacadeImpl implements CategoryFacade {
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public List<ReadableCategory> getCategoryHierarchy(MerchantStore store,
			int depth, Language language) throws Exception {
		
		List<Category> categories = categoryService.listByDepth(store, 2, language);
		List<ReadableCategory> returnValues = new ArrayList<ReadableCategory>();
		
		Map<Long, ReadableCategory> categoryMap = new ConcurrentHashMap<Long, ReadableCategory>();
		
		ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
		
		for(Category category : categories) {
			
			ReadableCategory readableCategory = new ReadableCategory();
			categoryPopulator.populate(category, readableCategory, store, language);
			
			//if(category.getParent()!=null) {
			if(category.getParent()!=null) {
				ReadableCategory parentCategory = categoryMap.get(category.getParent().getId());
				if(parentCategory!=null) {
					parentCategory.getChildren().add(readableCategory);
				}
			}
			categoryMap.put(category.getId(), readableCategory);
		}
		
		for(Object obj : categoryMap.values()) {
			
			ReadableCategory readableCategory = (ReadableCategory)obj;
			if(readableCategory.getDepth()==0) {//only from root
				returnValues.add(readableCategory);
			}
		}
		
		return returnValues;
	}

}
