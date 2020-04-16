package com.salesmanager.core.business.repositories.merchant;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

public interface MerchantRepositoryCustom {

  GenericEntityList<MerchantStore> listByCriteria(MerchantStoreCriteria criteria)
      throws ServiceException;


}
