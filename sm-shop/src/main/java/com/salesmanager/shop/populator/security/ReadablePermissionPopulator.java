package com.salesmanager.shop.populator.security;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.shop.model.security.ReadablePermission;

public class ReadablePermissionPopulator extends AbstractDataPopulator<Permission, ReadablePermission> {

  @Override
  public ReadablePermission populate(Permission source, ReadablePermission target,
      MerchantStore store, Language language) throws ConversionException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected ReadablePermission createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

}
