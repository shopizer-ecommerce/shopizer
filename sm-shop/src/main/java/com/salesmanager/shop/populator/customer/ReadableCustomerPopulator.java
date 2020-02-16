package com.salesmanager.shop.populator.customer;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionDescription;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.shop.model.customer.attribute.ReadableCustomerAttribute;
import com.salesmanager.shop.model.customer.attribute.ReadableCustomerOption;
import com.salesmanager.shop.model.customer.attribute.ReadableCustomerOptionValue;

public class ReadableCustomerPopulator extends
		AbstractDataPopulator<Customer, ReadableCustomer> {

	

	@Override
	public ReadableCustomer populate(Customer source, ReadableCustomer target,
			MerchantStore store, Language language) throws ConversionException {

		try {
			
			if(target == null) {
				target = new ReadableCustomer();
			}
			
		if(source.getId()!=null && source.getId()>0) {
			target.setId(source.getId());
		}
		target.setEmailAddress(source.getEmailAddress());
		if(source.getBilling()!=null) {
			Address address = new Address();
			address.setAddress(source.getBilling().getAddress());
			address.setCity(source.getBilling().getCity());
			address.setCompany(source.getBilling().getCompany());
			address.setFirstName(source.getBilling().getFirstName());
			address.setLastName(source.getBilling().getLastName());
			address.setPostalCode(source.getBilling().getPostalCode());
			address.setPhone(source.getBilling().getTelephone());
			if(source.getBilling().getCountry()!=null) {
				address.setCountry(source.getBilling().getCountry().getIsoCode());
			}
			if(source.getBilling().getZone()!=null) {
				address.setZone(source.getBilling().getZone().getCode());
			}
			
			target.setFirstName(address.getFirstName());
			target.setLastName(address.getLastName());
			
			target.setBilling(address);
		}
		
		if(source.getCustomerReviewAvg() != null) {
			target.setRating(source.getCustomerReviewAvg().doubleValue());
		}
		
		if(source.getCustomerReviewCount() != null) {
			target.setRatingCount(source.getCustomerReviewCount().intValue());
		}
		
		if(source.getDelivery()!=null) {
			Address address = new Address();
			address.setCity(source.getDelivery().getCity());
			address.setAddress(source.getDelivery().getAddress());
			address.setCompany(source.getDelivery().getCompany());
			address.setFirstName(source.getDelivery().getFirstName());
			address.setLastName(source.getDelivery().getLastName());
			address.setPostalCode(source.getDelivery().getPostalCode());
			address.setPhone(source.getDelivery().getTelephone());
			if(source.getDelivery().getCountry()!=null) {
				address.setCountry(source.getDelivery().getCountry().getIsoCode());
			}
			if(source.getDelivery().getZone()!=null) {
				address.setZone(source.getDelivery().getZone().getCode());
			}
			
			target.setDelivery(address);
		}
		
		if(source.getAttributes()!=null) {
			for(CustomerAttribute attribute : source.getAttributes()) {
				ReadableCustomerAttribute readableAttribute = new ReadableCustomerAttribute();
				readableAttribute.setId(attribute.getId());
				ReadableCustomerOption option = new ReadableCustomerOption();
				option.setId(attribute.getCustomerOption().getId());
				option.setCode(attribute.getCustomerOption().getCode());
				
				CustomerOptionDescription d = new CustomerOptionDescription();
				d.setDescription(attribute.getCustomerOption().getDescriptionsSettoList().get(0).getDescription());
				d.setName(attribute.getCustomerOption().getDescriptionsSettoList().get(0).getName());
				option.setDescription(d);
				
				readableAttribute.setCustomerOption(option);
				
				ReadableCustomerOptionValue optionValue = new ReadableCustomerOptionValue();
				optionValue.setId(attribute.getCustomerOptionValue().getId());
				CustomerOptionValueDescription vd = new CustomerOptionValueDescription();
				vd.setDescription(attribute.getCustomerOptionValue().getDescriptionsSettoList().get(0).getDescription());
				vd.setName(attribute.getCustomerOptionValue().getDescriptionsSettoList().get(0).getName());
				optionValue.setCode(attribute.getCustomerOptionValue().getCode());
				optionValue.setDescription(vd);
				
				
				readableAttribute.setCustomerOptionValue(optionValue);
				target.getAttributes().add(readableAttribute);
			}
		}
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		
		return target;
	}

	@Override
	protected ReadableCustomer createTarget() {
		return null;
	}

}
