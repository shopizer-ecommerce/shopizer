package com.salesmanager.shop.populator.store;


import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;

/**
 * Populates MerchantStore core entity model object
 * @author carlsamson
 *
 */
public class ReadableMerchantStorePopulator extends
		AbstractDataPopulator<MerchantStore, ReadableMerchantStore> {

	@Override
	public ReadableMerchantStore populate(MerchantStore source,
			ReadableMerchantStore target, MerchantStore store, Language language)
			throws ConversionException {
		
		
		target.setId(source.getId());
		target.setCode(source.getCode());
		if(source.getDefaultLanguage() != null) {
			target.setDefaultLanguage(source.getDefaultLanguage().getCode());
		}

		target.setCurrency(source.getCurrency().getCode());
		target.setPhone(source.getStorephone());
		
		ReadableAddress address = new ReadableAddress();
		address.setAddress(source.getStoreaddress());
		address.setCity(source.getStorecity());
		if(source.getCountry()!=null) {
			address.setCountry(source.getCountry().getIsoCode());
		}
		if(source.getZone()!=null) {
			address.setStateProvince(source.getZone().getCode());
		}
		address.setPostalCode(source.getStorepostalcode());

		target.setAddress(address);
		
		target.setCurrencyFormatNational(source.isCurrencyFormatNational());
		target.setEmail(source.getStoreEmailAddress());
		target.setId(source.getId());
		target.setInBusinessSince(source.getDateBusinessSince());
		
		
		List<Language> languages = source.getLanguages();
		if(!CollectionUtils.isEmpty(languages)) {
			
			List<String> langs = new ArrayList<String>();
			for(Language lang : languages) {
				langs.add(lang.getCode());
			}
			
			target.setSupportedLanguages(langs);
		}
		
		
		return target;
	}

	@Override
	protected ReadableMerchantStore createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
