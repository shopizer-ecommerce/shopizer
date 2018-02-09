/**
 * 
 */
package com.salesmanager.core.business.utils;

import java.util.Locale;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


/**
 * @author Umesh A
 *
 */
public abstract class AbstractDataPopulator<Source,Target> implements DataPopulator<Source, Target>
{

 
   
    private Locale locale;

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Locale getLocale() {
		return locale;
	}
	

	@Override
	public Target populate(Source source, MerchantStore store, Language language) throws ConversionException{
	   return populate(source,createTarget(), store, language);
	}
	
	protected abstract Target createTarget();

   

}
