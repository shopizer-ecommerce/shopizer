package com.salesmanager.core.business.repositories.merchant;

import com.salesmanager.core.model.merchant.MerchantStore;

public interface MerchantRepositoryCustom {


  MerchantStore findByCode(String code);

  MerchantStore getById(Integer merchantId);
}
