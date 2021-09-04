
package com.salesmanager.shop.populator.manufacturer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;


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
			target.setCode(source.getCode());
			

			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				Set<com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription> descriptions = new HashSet<com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription>();
				for(ManufacturerDescription description : source.getDescriptions()) {
					com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription desc = new com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription();
					if(desc.getId() != null && desc.getId().longValue()>0) {
						desc.setId(description.getId());
					}
					if(target.getDescriptions() != null) {
						for(com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription d : target.getDescriptions()) {
							if(d.getLanguage().getCode().equals(description.getLanguage()) || desc.getId() != null && d.getId().longValue() == desc.getId().longValue()) {
								desc = d;
							}
						}
					}
					
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
