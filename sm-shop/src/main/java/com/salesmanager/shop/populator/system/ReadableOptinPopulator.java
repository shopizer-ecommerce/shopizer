package com.salesmanager.shop.populator.system;

import org.apache.commons.lang.Validate;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.shop.model.system.ReadableOptin;

public class ReadableOptinPopulator extends AbstractDataPopulator<Optin, ReadableOptin> {

	@Override
	public ReadableOptin populate(Optin source, ReadableOptin target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(source,"Optin cannot be null");
		
		if(target==null) {
			target = new ReadableOptin();
		}
		
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		target.setEndDate(source.getEndDate());
		target.setId(source.getId());
		target.setOptinType(source.getOptinType().name());
		target.setStartDate(source.getStartDate());
		target.setStore(store.getCode());

		return target;
	}

	@Override
	protected ReadableOptin createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
