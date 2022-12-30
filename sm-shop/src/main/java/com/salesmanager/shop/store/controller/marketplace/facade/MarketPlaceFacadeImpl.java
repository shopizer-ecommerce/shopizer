package com.salesmanager.shop.store.controller.marketplace.facade;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.util.Optional;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.populator.system.ReadableOptinPopulator;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;

@Component
public class MarketPlaceFacadeImpl implements MarketPlaceFacade {

	@Inject
	private StoreFacade storeFacade;

	@Inject
	private OptinService optinService;

	@Override
	public ReadableMarketPlace get(String store, Language lang) {
		ReadableMerchantStore readableStore = storeFacade.getByCode(store, lang);
    return createReadableMarketPlace(readableStore);
	}

  private ReadableMarketPlace createReadableMarketPlace(ReadableMerchantStore readableStore) {
    //TODO add info from Entity
    ReadableMarketPlace marketPlace = new ReadableMarketPlace();
    marketPlace.setStore(readableStore);
    return marketPlace;
  }

  @Override
	public ReadableOptin findByMerchantAndType(MerchantStore store, OptinType type) {
		Optin optin = getOptinByMerchantAndType(store, type);
    return convertOptinToReadableOptin(store, optin);
	}

  private Optin getOptinByMerchantAndType(MerchantStore store, OptinType type) {
	  try{
      return Optional.ofNullable(optinService.getOptinByMerchantAndType(store, type))
          .orElseThrow(() -> new ResourceNotFoundException("Option not found"));
    } catch (ServiceException e) {
	    throw new ServiceRuntimeException(e);
    }

  }

  private ReadableOptin convertOptinToReadableOptin(MerchantStore store, Optin optin) {
	  try{
      ReadableOptinPopulator populator = new ReadableOptinPopulator();
      return populator.populate(optin, null, store, null);
    } catch (ConversionException e) {
	    throw new ConversionRuntimeException(e);
    }

  }

}
