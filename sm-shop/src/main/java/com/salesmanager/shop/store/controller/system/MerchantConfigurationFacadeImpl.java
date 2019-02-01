package com.salesmanager.shop.store.controller.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.system.Configs;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import static com.salesmanager.shop.constants.Constants.KEY_FACEBOOK_PAGE_URL;
import static com.salesmanager.shop.constants.Constants.KEY_GOOGLE_ANALYTICS_URL;
import static com.salesmanager.shop.constants.Constants.KEY_INSTAGRAM_URL;
import static com.salesmanager.shop.constants.Constants.KEY_PINTEREST_PAGE_URL;

@Service
public class MerchantConfigurationFacadeImpl implements MerchantConfigurationFacade {

  @Inject
  private MerchantConfigurationService merchantConfigurationService;

  @Override
  public Configs getMerchantConfig(MerchantStore merchantStore, Language language) {

    MerchantConfig configs = getMerchantConfig(merchantStore);

    Configs readableConfig = new Configs();
    readableConfig.setAllowOnlinePurchase(configs.isAllowPurchaseItems());
    readableConfig.setDisplaySearchBox(configs.isDisplaySearchBox());
    readableConfig.setDisplayContactUs(configs.isDisplayContactUs());

    Optional<String> facebookConfigValue = getConfigValue(KEY_FACEBOOK_PAGE_URL, merchantStore);
    facebookConfigValue.ifPresent(readableConfig::setFacebook);

    Optional<String> googleConfigValue = getConfigValue(KEY_GOOGLE_ANALYTICS_URL, merchantStore);
    googleConfigValue.ifPresent(readableConfig::setGa);

    Optional<String> instagramConfigValue = getConfigValue(KEY_INSTAGRAM_URL, merchantStore);
    instagramConfigValue.ifPresent(readableConfig::setInstagram);


    Optional<String> pinterestConfigValue = getConfigValue(KEY_PINTEREST_PAGE_URL, merchantStore);
    pinterestConfigValue.ifPresent(readableConfig::setPinterest);

    return readableConfig;
  }

  private MerchantConfig getMerchantConfig(MerchantStore merchantStore) {
    try{
      return merchantConfigurationService.getMerchantConfig(merchantStore);
    } catch (ServiceException e){
      throw new ServiceRuntimeException(e);
    }
  }

  private Optional<String> getConfigValue(String keyContant, MerchantStore merchantStore) {
    return getMerchantConfiguration(keyContant, merchantStore)
        .map(MerchantConfiguration::getValue);
  }

  private Optional<MerchantConfiguration> getMerchantConfiguration(String key, MerchantStore merchantStore) {
    try{
      return Optional.ofNullable(merchantConfigurationService.getMerchantConfiguration(key, merchantStore));
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }

  }
}
