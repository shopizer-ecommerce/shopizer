package com.salesmanager.core.business.utils;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public interface DataPopulator<Source, Target> {

  Target populate(Source source, Target target, MerchantStore store, Language language)
      throws ServiceException;

  Target populate(Source source, MerchantStore store, Language language)
      throws ServiceException;
}
