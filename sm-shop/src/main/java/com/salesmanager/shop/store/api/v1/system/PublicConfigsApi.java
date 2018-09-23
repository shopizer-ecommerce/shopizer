package com.salesmanager.shop.store.api.v1.system;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.system.Configs;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/v1")
public class PublicConfigsApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicConfigsApi.class);
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private MerchantConfigurationService merchantConfigurationService;
	
	/**
	 * Get public set of merchant configuration
	 * --- allow online purchase
	 * --- social links
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/config"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get public configuration for a given merchant store", notes = "", produces = "application/json", response = Configs.class)
	@ResponseBody
	public Configs box(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			
			MerchantConfig configs = merchantConfigurationService.getMerchantConfig(merchantStore);
			Configs c = new Configs();
			
			c.setAllowOnlinePurchase(configs.isAllowPurchaseItems());
			c.setDisplaySearchBox(configs.isDisplaySearchBox());
			c.setDisplayContactUs(configs.isDisplayContactUs());
			
			
			MerchantConfiguration merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_FACEBOOK_PAGE_URL,merchantStore);
			if(null != merchantConfiguration)
			{
				c.setFacebook(merchantConfiguration.getValue());
			}
			
			
			 merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_GOOGLE_ANALYTICS_URL,merchantStore);
			if(null != merchantConfiguration)
			{
				c.setGa(merchantConfiguration.getValue());
			}

			
			merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_INSTAGRAM_URL,merchantStore);
			if(null != merchantConfiguration)
			{
				c.setInstagram(merchantConfiguration.getValue());
			}

			
			merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_PINTEREST_PAGE_URL,merchantStore);
			if(null != merchantConfiguration)
			{
				c.setPinterest(merchantConfiguration.getValue());
			}
			
			return c;


			
		} catch (Exception e) {
			LOGGER.error("Error while getting public configs",e);
			try {
				response.sendError(503, "Error while getting public configs " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}

}
