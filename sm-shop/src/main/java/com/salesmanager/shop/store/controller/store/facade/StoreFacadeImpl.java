package com.salesmanager.shop.store.controller.store.facade;

import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.LanguageUtils;
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
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.constants.MeasureUnit;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.content.InputContentFile;
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
  private ContentService contentService;

  @Inject
  private PersistableMerchantStorePopulator persistableMerchantStorePopulator;

  @Inject
  @Qualifier("img")
  private ImageFilePath imageUtils;

  @Inject
  private LanguageUtils languageUtils;
  
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
  public ReadableMerchantStore getByCode(String code, String lang) {
    Language language = getLanguage(lang);
    return getByCode(code, language);
  }

  private Language getLanguage(String lang) {
    return languageUtils.getServiceLanguage(lang);
  }

  @Override
  public ReadableMerchantStore getByCode(String code, Language language) {
    MerchantStore store = getMerchantStoreByCode(code);
    return convertMerchantStoreToReadableMerchantStore(language, store);
  }

  private ReadableMerchantStore convertMerchantStoreToReadableMerchantStore(Language language,
      MerchantStore store) {
    ReadableMerchantStore readable = new ReadableMerchantStore();

    ReadableMerchantStorePopulator populator = new ReadableMerchantStorePopulator();
    populator.setCountryService(countryService);
    populator.setZoneService(zoneService);
    populator.setFilePath(imageUtils);

    /**
     * Language is not important for this conversion using default language
     */
    try {
      readable = populator.populate(store, readable, store, language);
    } catch(Exception e) {
      throw new ConversionRuntimeException("Error while populating MerchantStore " + e.getMessage());
    }
    return readable;
  }

  private MerchantStore getMerchantStoreByCode(String code) {
    return Optional.ofNullable(get(code))
        .orElseThrow(() -> new ResourceNotFoundException("Merchant store code not found"));
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
    
    MerchantStore mStore = getMerchantStoreByCode(store.getCode());

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
    MerchantStore mStore = getMerchantStoreByCode(code);
    
    try {
      merchantStoreService.delete(mStore);
    } catch(Exception e) {
      LOG.error("Error while deleting MerchantStore",e);
      throw new ServiceRuntimeException("Error while deleting MerchantStore " + e.getMessage());
    }
    
  }

  @Override
  public ReadableBrand getBrand(String code) {
    MerchantStore mStore = getMerchantStoreByCode(code);
    
    ReadableBrand readableBrand = new ReadableBrand();
    if(!StringUtils.isEmpty(mStore.getStoreLogo())) {
      ReadableImage image = new ReadableImage();
      image.setName(mStore.getStoreLogo());
      image.setPath(imageUtils.buildStoreLogoFilePath(mStore));
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

  @Override
  public void deleteLogo(String code) {
    MerchantStore store = getByCode(code);
    String image = store.getStoreLogo();
    store.setStoreLogo(null);
    
    try {
      merchantStoreService.update(store);
      if(!StringUtils.isEmpty(image)) {
        contentService.removeFile(store.getCode(), image);
      }
    } catch(Exception e) {
      throw new ServiceRuntimeException(e.getMessage());
    }
    
    
    
  }

  @Override
  public MerchantStore getByCode(String code) {
    return getMerchantStoreByCode(code);
  }

  @Override
  public void addStoreLogo(String code, InputContentFile cmsContentImage) throws Exception{
    MerchantStore store = getByCode(code);
    store.setStoreLogo(cmsContentImage.getFileName());
    merchantStoreService.save(store);
    contentService.addLogo(code, cmsContentImage);

  }

}
