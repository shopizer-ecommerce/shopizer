package com.salesmanager.shop.mapper.tax;

import org.jsoup.helper.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.tax.PersistableTaxRate;

@Component
public class PersistableTaxRateMapper implements Mapper<PersistableTaxRate, TaxRate> {

	@Override
	public TaxRate convert(PersistableTaxRate source, MerchantStore store, Language language) {
		TaxRate rate = new TaxRate();
		return this.convert(source, rate, store, language);
	}

	@Override
	public TaxRate convert(PersistableTaxRate source, TaxRate destination, MerchantStore store, Language language) {
		Validate.notNull(destination, "destination TaxRate cannot be null");
		Validate.notNull(source, "source TaxRate cannot be null");
		destination.setId(source.getId());
		destination.setCode(source.getCode());
		destination.setTaxPriority(source.getPriority());
		//destination.setCountry(country);
		//destination.setDescriptions(descriptions);
		destination.setMerchantStore(store);
		//destination.setStateProvince(stateProvince);
		//destination.setTaxClass(taxClass);
		destination.setTaxRate(source.getRate());
		//destination.setZone(zone);
		return null;
	}



}
