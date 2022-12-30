package com.salesmanager.shop.mapper.tax;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import com.salesmanager.core.model.tax.taxrate.TaxRateDescription;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.tax.ReadableTaxRate;
import com.salesmanager.shop.model.tax.ReadableTaxRateDescription;

@Component
public class ReadableTaxRateMapper implements Mapper<TaxRate, ReadableTaxRate> {

	@Override
	public ReadableTaxRate convert(TaxRate source, MerchantStore store, Language language) {
		ReadableTaxRate taxRate = new ReadableTaxRate();
		return this.merge(source, taxRate, store, language);

	}

	@Override
	public ReadableTaxRate merge(TaxRate source, ReadableTaxRate destination, MerchantStore store,
								 Language language) {
		Validate.notNull(destination, "destination TaxRate cannot be null");
		Validate.notNull(source, "source TaxRate cannot be null");
		destination.setId(source.getId());
		destination.setCountry(source.getCountry().getIsoCode());
		destination.setZone(source.getZone().getCode());
		destination.setRate(source.getTaxRate().toString());
		destination.setCode(source.getCode());
		destination.setPriority(source.getTaxPriority());
		Optional<ReadableTaxRateDescription> description = this.convertDescription(source.getDescriptions(), language);
		if(description.isPresent()) {
			destination.setDescription(description.get());
		}
		return destination;
	}
	
	private Optional<ReadableTaxRateDescription> convertDescription(List<TaxRateDescription> descriptions, Language language) {
		Validate.notEmpty(descriptions,"List of TaxRateDescriptions should not be empty");
		
	    Optional<TaxRateDescription> description = descriptions.stream()
	            .filter(desc -> desc.getLanguage().getCode().equals(language.getCode())).findAny();
	        if (description.isPresent()) {
	          return Optional.of(convertDescription(description.get()));
	        } else {
	          return Optional.empty();
	        }
		
		
	}
	
	private ReadableTaxRateDescription convertDescription(TaxRateDescription desc) {
		ReadableTaxRateDescription d = new ReadableTaxRateDescription();
		d.setDescription(desc.getDescription());
		d.setName(desc.getName());
		d.setLanguage(desc.getLanguage().getCode());
		d.setDescription(desc.getDescription());
		d.setId(desc.getId());
		d.setTitle(desc.getTitle());
		return d;
	}



}
