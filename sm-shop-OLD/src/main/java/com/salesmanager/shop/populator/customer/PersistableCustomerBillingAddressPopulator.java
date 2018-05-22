package com.salesmanager.shop.populator.customer;

import org.apache.commons.lang.StringUtils;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.shop.model.customer.Address;

public class PersistableCustomerBillingAddressPopulator extends AbstractDataPopulator<Address, Customer>
{

    @Override
    public Customer populate( Address source, Customer target, MerchantStore store, Language language )
        throws ConversionException
    {
        
       
           target.getBilling().setFirstName( source.getFirstName() );
           target.getBilling().setLastName( source.getLastName() );
          
            // lets fill optional data now
           
           if(StringUtils.isNotBlank( source.getAddress())){
               target.getBilling().setAddress( source.getAddress() ); 
           }
           
           if(StringUtils.isNotBlank( source.getCity())){
               target.getBilling().setCity( source.getCity() );
           }
           
           if(StringUtils.isNotBlank( source.getCompany())){
               target.getBilling().setCompany( source.getCompany() );
           }
           
           if(StringUtils.isNotBlank( source.getPhone())){
               target.getBilling().setTelephone( source.getPhone());
           }
           
           if(StringUtils.isNotBlank( source.getPostalCode())){
               target.getBilling().setPostalCode( source.getPostalCode());
           }
           
           if(StringUtils.isNotBlank( source.getStateProvince())){
               target.getBilling().setState(source.getStateProvince());
           }
           
           return target;
        
    }

    @Override
    protected Customer createTarget()
    {
         return null;
    }

   

}
