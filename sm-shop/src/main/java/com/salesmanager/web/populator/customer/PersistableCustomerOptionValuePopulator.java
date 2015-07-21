package com.salesmanager.web.populator.customer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.web.entity.customer.attribute.PersistableCustomerOptionValue;

public class PersistableCustomerOptionValuePopulator extends
		AbstractDataPopulator<PersistableCustomerOptionValue, CustomerOptionValue> {

	
	private LanguageService languageService;
	
	@Override
	public CustomerOptionValue populate(PersistableCustomerOptionValue source,
			CustomerOptionValue target, MerchantStore store, Language language)
			throws ConversionException {
		
		
		Validate.notNull(languageService, "Requires to set LanguageService");
		
		
		try {
			
			target.setCode(source.getCode());
			target.setMerchantStore(store);
			target.setSortOrder(source.getOrder());
			
			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				Set<com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription> descriptions = new HashSet<com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription>();
				for(CustomerOptionValueDescription desc  : source.getDescriptions()) {
					com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription description = new com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription();
					Language lang = languageService.getByCode(desc.getLanguage());
					if(lang==null) {
						throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
					}
					description.setLanguage(lang);
					description.setName(desc.getName());
					description.setTitle(desc.getTitle());
					description.setCustomerOptionValue(target);
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
	protected CustomerOptionValue createTarget() {
		return null;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}

}
