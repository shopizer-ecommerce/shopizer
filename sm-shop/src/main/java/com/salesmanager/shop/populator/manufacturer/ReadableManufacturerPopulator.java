package com.salesmanager.shop.populator.manufacturer;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;

import java.util.Set;

public class ReadableManufacturerPopulator extends AbstractDataPopulator<com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer,ReadableManufacturer>
{



	
	@Override
	public ReadableManufacturer populate(
			com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer source,
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
				target.setCode(source.getCode());
				
				if (description != null) {
					com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription d = new com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription();
					d.setName(description.getName());
					d.setDescription(description.getDescription());
					d.setId(description.getId());
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
