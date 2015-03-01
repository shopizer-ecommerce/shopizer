package com.salesmanager.web.populator.manufacturer;

import java.util.Set;

import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.manufacturer.ReadableManufacturer;

public class ReadableManufacturerPopulator extends AbstractDataPopulator<com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer,ReadableManufacturer>
{



	
	@Override
	public ReadableManufacturer populate(
			com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer source,
			ReadableManufacturer target, MerchantStore store, Language language) throws ConversionException {
		target.setId(source.getId());
		if(source.getDescriptions()!=null && source.getDescriptions().size()>0) {
			
				Set<ManufacturerDescription> descriptions = source.getDescriptions();
				ManufacturerDescription description = null;
				for(ManufacturerDescription desc : descriptions) {
					if(desc.getLanguage().getCode().equals(language.getCode())) {
						description = desc;
						break;
					}
				}
				
				target.setOrder(source.getOrder());
				target.setId(source.getId());
				
				if (description != null) {
					com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription d = new com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription();
					d.setName(description.getName());
					d.setDescription(description.getDescription());
					target.setDescription(d);
				}

		}

		return target;
	}

    @Override
    protected ReadableManufacturer createTarget()
    {
        return null;
    }
}
