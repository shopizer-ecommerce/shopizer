/**
 *
 */
package com.salesmanager.shop.populator.customer;

import org.apache.commons.lang.StringUtils;


import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.CustomerEntity;



/**
 * <p>
 * CustomerEntityPopulator will help to populate {@link CustomerEntity} from {@link Customer} CustomerEntity will be
 * used to show data on the UI side.
 * </p>
 *
 * @author Umesh Awasthi
 * @version 1.2
 */
public class CustomerEntityPopulator
    extends AbstractDataPopulator<Customer, CustomerEntity>
{

    @Override
    public CustomerEntity populate( final Customer source, final CustomerEntity target,
                                    final MerchantStore merchantStore, final Language language )
        throws ConversionException
    {
        try
        {

            
            target.setId( source.getId() );
            if(StringUtils.isNotBlank( source.getEmailAddress() )){
                target.setEmailAddress( source.getEmailAddress() );
            }
            
          

            if ( source.getBilling() != null )
            {
                Address address = new Address();
                address.setCity( source.getBilling().getCity() );
                address.setAddress(source.getBilling().getAddress());
                address.setCompany( source.getBilling().getCompany() );
                address.setFirstName( source.getBilling().getFirstName() );
                address.setLastName( source.getBilling().getLastName() );
                address.setPostalCode( source.getBilling().getPostalCode() );
                address.setPhone( source.getBilling().getTelephone() );
                if ( source.getBilling().getCountry() != null )
                {
                    address.setCountry( source.getBilling().getCountry().getIsoCode() );
                }
                if ( source.getBilling().getZone() != null )
                {
                    address.setZone( source.getBilling().getZone().getCode() );
                }
                
                address.setStateProvince(source.getBilling().getState());

                target.setBilling( address );
            }
            
    		if(source.getCustomerReviewAvg() != null) {
    			target.setRating(source.getCustomerReviewAvg().doubleValue());
    		}
    		
    		if(source.getCustomerReviewCount() != null) {
    			target.setRatingCount(source.getCustomerReviewCount().intValue());
    		}

            if ( source.getDelivery() != null )
            {
                Address address = new Address();
                address.setCity( source.getDelivery().getCity() );
                address.setAddress(source.getDelivery().getAddress());
                address.setCompany( source.getDelivery().getCompany() );
                address.setFirstName( source.getDelivery().getFirstName() );
                address.setLastName( source.getDelivery().getLastName() );
                address.setPostalCode( source.getDelivery().getPostalCode() );
                address.setPhone( source.getDelivery().getTelephone() );
                if ( source.getDelivery().getCountry() != null )
                {
                    address.setCountry( source.getDelivery().getCountry().getIsoCode() );
                }
                if ( source.getDelivery().getZone() != null )
                {
                    address.setZone( source.getDelivery().getZone().getCode() );
                }
                
                address.setStateProvince(source.getDelivery().getState());

                target.setDelivery( address );
            }

        }
        catch ( Exception e )
        {
            throw new ConversionException( e );
        }

        return target;
    }

    @Override
    protected CustomerEntity createTarget()
    {
        return new CustomerEntity();
    }

}
