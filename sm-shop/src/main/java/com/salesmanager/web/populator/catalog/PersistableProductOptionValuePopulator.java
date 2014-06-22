package com.salesmanager.web.populator.catalog;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValue;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.web.entity.catalog.product.attribute.ProductOptionValueDescription;

/**
 * Converts a PersistableProductOptionValue to
 * a ProductOptionValue model object
 * @author Carl Samson
 *
 */
public class PersistableProductOptionValuePopulator extends
		AbstractDataPopulator<PersistableProductOptionValue, ProductOptionValue> {

	
	private LanguageService languageService;
	
	public LanguageService getLanguageService() {
		return languageService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	@Override
	public ProductOptionValue populate(PersistableProductOptionValue source,
			ProductOptionValue target, MerchantStore store, Language language)
			throws ConversionException {
		
		Validate.notNull(languageService, "Requires to set LanguageService");
		
		
		try {
			

			target.setMerchantStore(store);
			target.setProductOptionValueSortOrder(source.getOrder());
			target.setCode(source.getCode());
			
			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				Set<com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValueDescription> descriptions = new HashSet<com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValueDescription>();
				for(ProductOptionValueDescription desc  : source.getDescriptions()) {
					com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValueDescription description = new com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValueDescription();
					Language lang = languageService.getByCode(desc.getLanguage());
					if(lang==null) {
						throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
					}
					description.setLanguage(lang);
					description.setName(desc.getName());
					description.setTitle(desc.getTitle());
					description.setProductOptionValue(target);
					descriptions.add(description);
				}
				target.setDescriptions(descriptions);
			}
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		
		
		return target;
	}

	@Override
	protected ProductOptionValue createTarget() {
		return null;
	}

}
