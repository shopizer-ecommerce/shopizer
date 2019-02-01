package com.salesmanager.shop.store.api.v1.system;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.system.Configs;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.system.MerchantConfigurationFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.ApiOperation;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PublicConfigsApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicConfigsApi.class);
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private MerchantConfigurationFacade configurationFacade;

  /**
   * Get public set of merchant configuration --- allow online purchase --- social links
   *
   * @param storeCode
   * @param request
   * @return
   */
  @GetMapping("/config")
  @ApiOperation(
      httpMethod = "GET",
      value = "Get public configuration for a given merchant store",
      notes = "",
      produces = "application/json",
      response = Configs.class)
  public Configs getConfig(
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {
			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
      return configurationFacade.getMerchantConfig(merchantStore, language);
	}

}
