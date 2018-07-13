package com.salesmanager.core.business.utils;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.merchant.MerchantStore;


/**
 * @author Umesh A
 */
public interface EntityPopulator<Source, Target> {

  Target populateToEntity(Source source, Target target, MerchantStore store)
      throws ServiceException;

  Target populateToEntity(Source source) throws ServiceException;
}
