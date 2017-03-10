package com.salesmanager.admin.utils;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Created by umesh on 3/9/17.  
 */
public class LocaleUtils {
    
    //// TODO: 3/9/17 this needs to be moved to core api

    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleUtils.class);

    public static Locale getLocale(Language language) {

        return new Locale(language.getCode());

    }

    /**
     * Creates a Locale object for currency format only with country code
     * This method ignoes the language
     * @param store
     * @return
     */
    public static Locale getLocale(MerchantStore store) {

        Locale defaultLocale = Constants.DEFAULT_LOCALE;
        Locale[] locales = Locale.getAvailableLocales();
        for(int i = 0; i< locales.length; i++) {
            Locale l = locales[i];
            try {
                if(l.getISO3Country().equals(store.getCurrency().getCode())) {
                    defaultLocale = l;
                    break;
                }
            } catch(Exception e) {
                LOGGER.error("An error occured while getting ISO code for locale " + l.toString());
            }
        }

        return defaultLocale;

    }
}
