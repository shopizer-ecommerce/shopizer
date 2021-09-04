package com.salesmanager.shop.populator.customer;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.address.Address;

public class PersistableCustomerShippingAddressPopulator extends AbstractDataPopulator<Address, Customer>
{

    @Override
    public Customer populate( Address source, Customer target, MerchantStore store, Language language )
        throws ConversionException
    {
        
       
          if( target.getDelivery() == null){
              
              Delivery delivery=new Delivery();
              delivery.setFirstName( source.getFirstName()) ;
              delivery.setLastName( source.getLastName() );
              
              if(StringUtils.isNotBlank( source.getAddress())){
                  delivery.setAddress( source.getAddress() ); 
              }
              
              if(StringUtils.isNotBlank( source.getCity())){
                  delivery.setCity( source.getCity() );
              }
              
              if(StringUtils.isNotBlank( source.getCompany())){
                  delivery.setCompany( source.getCompany() );
              }
              
              if(StringUtils.isNotBlank( source.getPhone())){
                  delivery.setTelephone( source.getPhone());
              }
              
              if(StringUtils.isNotBlank( source.getPostalCode())){
                  delivery.setPostalCode( source.getPostalCode());
              }
              
              if(StringUtils.isNotBlank( source.getStateProvince())){
                  delivery.setPostalCode( source.getStateProvince());
              }
              
              target.setDelivery( delivery );
          }
          else{
           target.getDelivery().setFirstName( source.getFirstName() );
           target.getDelivery().setLastName( source.getLastName() );
          
            // lets fill optional data now
           
           if(StringUtils.isNotBlank( source.getAddress())){
               target.getDelivery().setAddress( source.getAddress() ); 
           }
           
           if(StringUtils.isNotBlank( source.getCity())){
               target.getDelivery().setCity( source.getCity() );
           }
           
           if(StringUtils.isNotBlank( source.getCompany())){
               target.getDelivery().setCompany( source.getCompany() );
           }
           
           if(StringUtils.isNotBlank( source.getPhone())){
               target.getDelivery().setTelephone( source.getPhone());
           }
           
           if(StringUtils.isNotBlank( source.getPostalCode())){
               target.getDelivery().setPostalCode( source.getPostalCode());
           }
           
           if(StringUtils.isNotBlank( source.getStateProvince())){
               target.getDelivery().setPostalCode( source.getStateProvince());
           }
          }
           
           return target;
        
    }

    @Override
    protected Customer createTarget()
    {
         return null;
    }

   

}
