/**
 * 
 */
package com.salesmanager.core.business.utils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * @author Umesh A
 *
 */
public interface EntityPopulator<Source,Target>
{

    Target populateToEntity(Source source, Target target, MerchantStore store)  throws ConversionException;
    Target populateToEntity(Source source) throws ConversionException;
}
