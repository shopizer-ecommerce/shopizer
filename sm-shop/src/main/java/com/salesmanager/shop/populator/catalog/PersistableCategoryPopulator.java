package com.salesmanager.shop.populator.catalog;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


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
		target.setFeatured(source.isFeatured());
		
		//get parent
		
		if(source.getParent()==null) {

			target.setParent(null);
			target.setLineage("/");
			target.setDepth(0);

		} else {
			Category parent = null;
			if(!StringUtils.isBlank(source.getParent().getCode())) {
				 parent = categoryService.getByCode(store.getCode(), source.getParent().getCode());
			} else if(source.getParent().getId()!=null) {
				 parent = categoryService.getById(source.getParent().getId());
			} else {
				throw new ConversionException("Category parent needs at least an id or a code for reference");
			}
			if(parent !=null && parent.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				throw new ConversionException("Store id does not belong to specified parent id");
			}
			
			if(parent!=null) {
				target.setParent(parent);
				
				String lineage = parent.getLineage();
				int depth = parent.getDepth();
	
				target.setDepth(depth+1);
				target.setLineage(new StringBuilder().append(lineage).append(parent.getId()).append("/").toString());
			}

		}
		
		
		if(!CollectionUtils.isEmpty(source.getChildren())) {
			
			for(PersistableCategory cat : source.getChildren()) {
				
				Category persistCategory = this.populate(cat, new Category(), store, language);
				target.getCategories().add(persistCategory);
				
			}
			
		}

		
		if(!CollectionUtils.isEmpty(source.getDescriptions())) {
			List<com.salesmanager.core.model.catalog.category.CategoryDescription> descriptions = new ArrayList<com.salesmanager.core.model.catalog.category.CategoryDescription>();
			for(CategoryDescription description : source.getDescriptions()) {
				com.salesmanager.core.model.catalog.category.CategoryDescription desc = new com.salesmanager.core.model.catalog.category.CategoryDescription();
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
