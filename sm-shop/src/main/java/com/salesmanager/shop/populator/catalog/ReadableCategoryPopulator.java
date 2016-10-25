package com.salesmanager.web.populator.catalog;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;

public class ReadableCategoryPopulator extends
		AbstractDataPopulator<Category, ReadableCategory> {

	@Override
	public ReadableCategory populate(Category source, ReadableCategory target,
			MerchantStore store, Language language) throws ConversionException {
		
		
		target.setLineage(source.getLineage());
		if(source.getDescriptions()!=null && source.getDescriptions().size()>0) {
			
			CategoryDescription description = source.getDescription();
			if(source.getDescriptions().size()>1) {
				for(CategoryDescription desc : source.getDescriptions()) {
					if(desc.getLanguage().getCode().equals(language.getCode())) {
						description = desc;
						break;
					}
				}
			}
		
		
		
			if(description!=null) {
				com.salesmanager.web.entity.catalog.category.CategoryDescription desc = new com.salesmanager.web.entity.catalog.category.CategoryDescription();
				desc.setFriendlyUrl(description.getSeUrl());
				desc.setName(description.getName());
				desc.setDescription(description.getName());
				desc.setKeyWords(description.getMetatagKeywords());
				desc.setHighlights(description.getCategoryHighlight());
				desc.setTitle(description.getMetatagTitle());
				desc.setMetaDescription(description.getMetatagDescription());
				
				target.setDescription(desc);
			}
		
		}
		
		if(source.getParent()!=null) {
			com.salesmanager.web.entity.catalog.category.Category parent = new com.salesmanager.web.entity.catalog.category.Category();
			parent.setCode(source.getParent().getCode());
			parent.setId(source.getParent().getId());
			target.setParent(parent);
		}
		
		target.setCode(source.getCode());
		target.setId(source.getId());
		target.setDepth(source.getDepth());
		target.setSortOrder(source.getSortOrder());
		target.setVisible(source.isVisible());

		return target;
		
	}

	@Override
	protected ReadableCategory createTarget() {
		return null;
	}

}
