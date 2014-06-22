
package com.salesmanager.web.populator.manufacturer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.web.entity.catalog.manufacturer.PersistableManufacturer;


/**
 * @author Carl Samson
 *
 */


public class PersistableManufacturerPopulator extends AbstractDataPopulator<PersistableManufacturer, Manufacturer>
{
	
	
	private LanguageService languageService;

	@Override
	public Manufacturer populate(PersistableManufacturer source,
			Manufacturer target, MerchantStore store, Language language)
			throws ConversionException {
		
		Validate.notNull(languageService, "Requires to set LanguageService");
		
		try {
			
			target.setMerchantStore(store);
			

			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				Set<com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription> descriptions = new HashSet<com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription>();
				for(ManufacturerDescription description : source.getDescriptions()) {
					com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription desc = new com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription();
					desc.setManufacturer(target);
					desc.setDescription(description.getDescription());
					desc.setName(description.getName());
					Language lang = languageService.getByCode(description.getLanguage());
					if(lang==null) {
						throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
					}
					desc.setLanguage(lang);
					descriptions.add(desc);
				}
				target.setDescriptions(descriptions);
			}
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	
		
		return target;
	}

	@Override
	protected Manufacturer createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}


}
