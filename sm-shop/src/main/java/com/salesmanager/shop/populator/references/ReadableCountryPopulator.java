package com.salesmanager.shop.populator.references;

import org.apache.commons.collections4.CollectionUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.reference.zone.ZoneDescription;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.model.references.ReadableZone;

public class ReadableCountryPopulator extends AbstractDataPopulator<Country, ReadableCountry> {

	@Override
	public ReadableCountry populate(Country source, ReadableCountry target, MerchantStore store, Language language)
			throws ConversionException {
		
		if(target==null) {
			target = new ReadableCountry();
		}
		
		target.setId(new Long(source.getId()));
		target.setCode(source.getIsoCode());
		target.setSupported(source.getSupported());
		if(!CollectionUtils.isEmpty(source.getDescriptions())) {
			target.setName(source.getDescriptions().iterator().next().getName());
	    }
		
		if(!CollectionUtils.isEmpty(source.getZones())) {
			for(Zone z : source.getZones()) {
				ReadableZone readableZone = new ReadableZone();
				readableZone.setCountryCode(target.getCode());
				readableZone.setId(z.getId());
				if(!CollectionUtils.isEmpty(z.getDescriptions())) {
					for(ZoneDescription d : z.getDescriptions()) {
						if(d.getLanguage().getId() == language.getId()) {
							readableZone.setName(d.getName());
							continue;
						}
					}
				}
				target.getZones().add(readableZone);
			}
		}
		
		return target;
	}

	@Override
	protected ReadableCountry createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
