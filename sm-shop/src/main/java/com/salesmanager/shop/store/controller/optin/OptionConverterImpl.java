package com.salesmanager.shop.store.controller.optin;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.model.system.ReadableOptin;
import org.springframework.stereotype.Component;

@Component
public class OptionConverterImpl implements OptinConverter<ReadableOptin, Optin> {

  @Override
  public Optin convertTo(ReadableOptin source, MerchantStore merchantStore, Language language) {
    Optin optinEntity = new Optin();
    optinEntity.setCode(source.getCode());
    optinEntity.setDescription(source.getDescription());
    optinEntity.setOptinType(OptinType.valueOf(source.getOptinType()));
    optinEntity.setMerchant(merchantStore);
    return optinEntity;
  }

  @Override
  public ReadableOptin convertFrom(Optin source, MerchantStore merchantStore, Language language) {
    ReadableOptin readable = new ReadableOptin();
    readable.setCode(source.getCode());
    readable.setDescription(source.getDescription());
    readable.setOptinType(source.getOptinType().toString());
    return readable;
  }
}
