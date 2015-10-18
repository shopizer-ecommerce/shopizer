/**
 * 
 */
package com.salesmanager.core.utils;

import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;

/**
 * @author Umesh A
 *
 */
public interface EntityPopulator<Source,Target>
{

    public Target populateToEntity(Source source, Target target, MerchantStore store)  throws ConversionException;
    public Target populateToEntity(Source source) throws ConversionException;
}
