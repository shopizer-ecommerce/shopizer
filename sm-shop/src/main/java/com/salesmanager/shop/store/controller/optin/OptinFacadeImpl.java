package com.salesmanager.shop.store.controller.optin;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class OptinFacadeImpl implements OptinFacade {

  @Inject
  private OptinService optinService;

  @Inject
  private OptinConverter<ReadableOptin, Optin> converter;

  @Override
  public ReadableOptin create(
      ReadableOptin readableOptin, MerchantStore merchantStore, Language language) {
    Optin optinEntity = converter.convertTo(readableOptin, merchantStore, language);
    Optin savedOptinEntity = createOption(optinEntity);
    return converter.convertFrom(savedOptinEntity, merchantStore, language);
  }

  private Optin createOption(Optin optinEntity) {
    try{
      optinService.create(optinEntity);
      return optinEntity;
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }

  }
}
