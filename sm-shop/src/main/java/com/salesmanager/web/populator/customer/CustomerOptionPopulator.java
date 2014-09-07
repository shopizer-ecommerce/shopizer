package com.salesmanager.web.populator.customer;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.admin.entity.customer.attribute.CustomerOption;
import com.salesmanager.web.admin.entity.customer.attribute.CustomerOptionValue;

public class CustomerOptionPopulator extends
		AbstractDataPopulator<com.salesmanager.core.business.customer.model.attribute.CustomerOption, com.salesmanager.web.admin.entity.customer.attribute.CustomerOption> {

	
	private CustomerOptionSet optionSet;
	
	public CustomerOptionSet getOptionSet() {
		return optionSet;
	}

	public void setOptionSet(CustomerOptionSet optionSet) {
		this.optionSet = optionSet;
	}

	@Override
	public CustomerOption populate(
			com.salesmanager.core.business.customer.model.attribute.CustomerOption source,
			CustomerOption target, MerchantStore store, Language language) throws ConversionException {
		
		
		CustomerOption customerOption = target;
		if(customerOption==null) {
			customerOption = new CustomerOption();
		} 
		
		customerOption.setId(source.getId());
		customerOption.setType(source.getCustomerOptionType());
		customerOption.setName(source.getDescriptionsSettoList().get(0).getName());

		List<CustomerOptionValue> values = customerOption.getAvailableValues();
		if(values==null) {
			values = new ArrayList<CustomerOptionValue>();
			customerOption.setAvailableValues(values);
		}
		
		com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue optionValue = optionSet.getCustomerOptionValue();
		CustomerOptionValue custOptValue = new CustomerOptionValue();
		custOptValue.setId(optionValue.getId());
		custOptValue.setLanguage(language.getCode());
		custOptValue.setName(optionValue.getDescriptionsSettoList().get(0).getName());
		values.add(custOptValue);
		
		return customerOption;

	}

    @Override
    protected CustomerOption createTarget()
    {
        // TODO Auto-generated method stub
        return null;
    }


}
