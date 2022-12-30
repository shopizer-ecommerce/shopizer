package com.salesmanager.shop.populator.customer;

/**
 * Used in the admin section
 * @author c.samson
 *
 */

public class CustomerOptionPopulator {
	
	/**
	extends
}
		AbstractDataPopulator<com.salesmanager.core.model.customer.attribute.CustomerOption, com.salesmanager.shop.admin.model.customer.attribute.CustomerOption> {

	
	private CustomerOptionSet optionSet;
	
	public CustomerOptionSet getOptionSet() {
		return optionSet;
	}

	public void setOptionSet(CustomerOptionSet optionSet) {
		this.optionSet = optionSet;
	}

	@Override
	public CustomerOption populate(
			com.salesmanager.core.model.customer.attribute.CustomerOption source,
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
		
		com.salesmanager.core.model.customer.attribute.CustomerOptionValue optionValue = optionSet.getCustomerOptionValue();
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

	@Override
	public CustomerOption populate(com.salesmanager.core.model.customer.attribute.CustomerOption source,
			CustomerOption target, MerchantStore store, Language language) throws ConversionException {
		// TODO Auto-generated method stub
		return null;
	}
	**/


}
