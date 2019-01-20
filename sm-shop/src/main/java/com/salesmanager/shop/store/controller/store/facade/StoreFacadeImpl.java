package com.salesmanager.shop.store.controller.store.facade;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.Validate;
import org.drools.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.constants.MeasureUnit;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;
import com.salesmanager.shop.model.content.ReadableImage;
import com.salesmanager.shop.model.shop.MerchantConfigEntity;
import com.salesmanager.shop.model.shop.PersistableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableBrand;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStoreList;
import com.salesmanager.shop.populator.store.PersistableMerchantStorePopulator;
import com.salesmanager.shop.populator.store.ReadableMerchantStorePopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.utils.ImageFilePath;

@Service("storeFacade")
public class StoreFacadeImpl implements StoreFacade {

  @Inject
  private MerchantStoreService merchantStoreService;
  
  @Inject
  private MerchantConfigurationService merchantConfigurationService;

  @Inject
  private LanguageService languageService;

  @Inject
  private CountryService countryService;

  @Inject
  private ZoneService zoneService;


  @Inject
  private PersistableMerchantStorePopulator persistableMerchantStorePopulator;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;
  
  private static final Logger LOG = LoggerFactory.getLogger(StoreFacadeImpl.class);

  @Override
  public MerchantStore getByCode(HttpServletRequest request){
    String code = request.getParameter("store");
    if (StringUtils.isEmpty(code)) {
      code = com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
    }
    return get(code);
  }

  @Override
  public MerchantStore get(String code) {
    try {
        return merchantStoreService.getByCode(code);
    } catch (ServiceException e){
        LOG.error("Error while getting MerchantStore",e);
        throw new ServiceRuntimeException(e);
    }

  }

  @Override
  public ReadableMerchantStore getByCode(String code, Language language) {
    
    MerchantStore store = Optional.ofNullable(get(code))
        .orElseThrow(() -> new ResourceNotFoundException("Merchant store code not found"));

    ReadableMerchantStorePopulator populator = new ReadableMerchantStorePopulator();

    ReadableMerchantStore readable = new ReadableMerchantStore();

    populator.setCountryService(countryService);
    populator.setZoneService(zoneService);
    populator.setFilePath(imageUtils);

    /**
     * Language is not important for this conversion using default language
     */
    try {
      readable = populator.populate(store, readable, store, language);
      
    } catch(Exception e) {
      LOG.error("Error while populating MerchantStore",e);
      throw new ServiceRuntimeException("Error while populating MerchantStore " + e.getMessage());
    }
    return readable;

  }

  @Override
  public void create(PersistableMerchantStore store) throws Exception {

    Validate.notNull(store, "PersistableMerchantStore must not be null");
    Validate.notNull(store.getCode(), "PersistableMerchantStore.code must not be null");


    MerchantStore mStore = new MerchantStore();


    // set default values
    mStore.setWeightunitcode(MeasureUnit.KG.name());
    mStore.setSeizeunitcode(MeasureUnit.IN.name());

    mStore = persistableMerchantStorePopulator.populate(store, mStore,
        languageService.defaultLanguage());

    merchantStoreService.create(mStore);

  }

  @Override
  public void update(PersistableMerchantStore store) throws Exception {
    
    Validate.notNull(store);
    
    MerchantStore mStore = Optional.ofNullable(get(store.getCode()))
        .orElseThrow(() -> new ResourceNotFoundException("Merchant store code not found"));

    store.setId(mStore.getId());

    mStore = persistableMerchantStorePopulator.populate(store, mStore,
        languageService.defaultLanguage());

    merchantStoreService.update(mStore);

  }

  @Override
  public ReadableMerchantStoreList getByCriteria(MerchantStoreCriteria criteria, Language lang)
      throws Exception {
    
    GenericEntityList<MerchantStore> list = Optional.ofNullable(merchantStoreService.getByCriteria(criteria))
        .orElseThrow(() -> new ResourceNotFoundException("Criteria did not match any store"));

    List<MerchantStore> stores = list.getList();
    ReadableMerchantStorePopulator populator = new ReadableMerchantStorePopulator();
    populator.setCountryService(countryService);
    populator.setZoneService(zoneService);
    populator.setFilePath(imageUtils);

    ReadableMerchantStoreList returnList = new ReadableMerchantStoreList();
    returnList.setTotalCount(list.getTotalCount());

    for (MerchantStore store : stores) {


      ReadableMerchantStore readable = new ReadableMerchantStore();
      readable = populator.populate(store, readable, store, lang);
      returnList.getData().add(readable);

    }

    return returnList;
  }

  @Override
  public void delete(String code) {
    MerchantStore mStore = Optional.ofNullable(get(code))
        .orElseThrow(() -> new ResourceNotFoundException("Merchant store code not found"));
    
    try {
      merchantStoreService.delete(mStore);
    } catch(Exception e) {
      LOG.error("Error while deleting MerchantStore",e);
      throw new ServiceRuntimeException("Error while deleting MerchantStore " + e.getMessage());
    }
    
  }

  @Override
  public ReadableBrand getBrand(String code) {
    MerchantStore mStore = Optional.ofNullable(get(code))
        .orElseThrow(() -> new ResourceNotFoundException("Merchant store code not found"));
    
    ReadableBrand readableBrand = new ReadableBrand();
    if(!StringUtils.isEmpty(mStore.getStoreLogo())) {
      ReadableImage image = new ReadableImage();
      image.setName(mStore.getStoreLogo());
      imageUtils.buildStoreLogoFilePath(mStore);
      readableBrand.setLogo(image);
    }
    try {
      List<MerchantConfiguration> configurations = merchantConfigurationService.listByType(MerchantConfigurationType.SOCIAL, mStore);
      for(MerchantConfiguration config : configurations) {
        MerchantConfigEntity conf = new MerchantConfigEntity();
        conf.setId(config.getId());
        conf.setKey(config.getKey());
        conf.setType(MerchantConfigurationType.SOCIAL);
        conf.setValue(config.getValue());
        readableBrand.getSocialNetworks().add(conf);
      }
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error wile getting merchantConfigurations " + e.getMessage());
    }
    
    return readableBrand;
  }

}
