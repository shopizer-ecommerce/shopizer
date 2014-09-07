package com.salesmanager.web.populator.catalog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.category.CategoryDescription;
import com.salesmanager.web.entity.catalog.category.PersistableCategory;

public class PersistableCategoryPopulator extends
		AbstractDataPopulator<PersistableCategory, Category> {
	
	
	private CategoryService categoryService;
	private LanguageService languageService;


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}


	@Override
	public Category populate(PersistableCategory source, Category target,
			MerchantStore store, Language language)
			throws ConversionException {
		
		try {

		
		Validate.notNull(categoryService, "Requires to set CategoryService");
		Validate.notNull(languageService, "Requires to set LanguageService");
		
		target.setMerchantStore(store);
		target.setCode(source.getCode());
		target.setSortOrder(source.getSortOrder());
		target.setVisible(source.isVisible());
		
		//get parent
		
		if(source.getParent()==null) {

			target.setParent(null);
			target.setLineage("/");
			target.setDepth(0);

		} else {
			
			Category parent = categoryService.getById(source.getParent().getId());
			if(parent.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				throw new ConversionException("Store id does not belong to specified parent id");
			}
			
			target.setParent(parent);
			
			String lineage = parent.getLineage();
			int depth = parent.getDepth();

			target.setDepth(depth+1);
			target.setLineage(new StringBuilder().append(lineage).append(parent.getId()).append("/").toString());
			
			
			
		}
		

		
		if(!CollectionUtils.isEmpty(source.getDescriptions())) {
			List<com.salesmanager.core.business.catalog.category.model.CategoryDescription> descriptions = new ArrayList<com.salesmanager.core.business.catalog.category.model.CategoryDescription>();
			for(CategoryDescription description : source.getDescriptions()) {
				com.salesmanager.core.business.catalog.category.model.CategoryDescription desc = new com.salesmanager.core.business.catalog.category.model.CategoryDescription();
				desc.setCategory(target);
				desc.setCategoryHighlight(description.getHighlights());
				desc.setDescription(description.getDescription());
				desc.setName(description.getName());
				desc.setMetatagDescription(description.getMetaDescription());
				desc.setMetatagTitle(description.getTitle());
				desc.setSeUrl(description.getFriendlyUrl());
				Language lang = languageService.getByCode(description.getLanguage());
				if(lang==null) {
					throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
				}
				desc.setLanguage(lang);
				descriptions.add(desc);
			}
			target.setDescriptions(descriptions);
		}
	
		
		return target;
		
		
		} catch(Exception e) {
			throw new ConversionException(e);
		}

	}


	@Override
	protected Category createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
