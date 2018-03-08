package com.salesmanager.shop.populator.customer;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.PersistableCustomer;

public class PersistableCustomerPopulator extends
		AbstractDataPopulator<Customer, PersistableCustomer> {

	@Override
	public PersistableCustomer populate(Customer source,
			PersistableCustomer target, MerchantStore store, Language language)
			throws ConversionException {

		
		try {
			

			if(source.getBilling()!=null) {
				Address address = new Address();
				address.setCity(source.getBilling().getCity());
				address.setCompany(source.getBilling().getCompany());
				address.setFirstName(source.getBilling().getFirstName());
				address.setLastName(source.getBilling().getLastName());
				address.setPostalCode(source.getBilling().getPostalCode());
				address.setPhone(source.getBilling().getTelephone());
				if(source.getBilling().getTelephone()==null) {
					address.setPhone(source.getBilling().getTelephone());
				}
				address.setAddress(source.getBilling().getAddress());
				if(source.getBilling().getCountry()!=null) {
					address.setCountry(source.getBilling().getCountry().getIsoCode());
				}
				if(source.getBilling().getZone()!=null) {
					address.setZone(source.getBilling().getZone().getCode());
				}
				
				target.setBilling(address);
			}
			
			target.setProvider(source.getProvider());
			
			if(source.getCustomerReviewAvg() != null) {
				target.setRating(source.getCustomerReviewAvg().doubleValue());
			}
			
			if(source.getCustomerReviewCount() != null) {
				target.setRatingCount(source.getCustomerReviewCount().intValue());
			}
			
			if(source.getDelivery()!=null) {
				Address address = new Address();
				address.setAddress(source.getDelivery().getAddress());
				address.setCity(source.getDelivery().getCity());
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
			
			target.setId(source.getId());
			target.setEmailAddress(source.getEmailAddress());
			if(source.getGender()!=null) {
				target.setGender(source.getGender().name());
			}
			if(source.getDefaultLanguage()!=null) {
				target.setLanguage(source.getDefaultLanguage().getCode());
			}
			target.setUserName(source.getNick());
			target.setStoreCode(store.getCode());
			if(source.getDefaultLanguage()!=null) {
				target.setLanguage(source.getDefaultLanguage().getCode());
			} else {
				target.setLanguage(store.getDefaultLanguage().getCode());
			}
			
			
			
		} catch (Exception e) {
			throw new ConversionException(e);
		}
			
		return target;
		
	}

	@Override
	protected PersistableCustomer createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
