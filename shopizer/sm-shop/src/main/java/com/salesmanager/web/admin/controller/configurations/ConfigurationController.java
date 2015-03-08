package com.salesmanager.web.admin.controller.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantConfiguration;
import com.salesmanager.core.business.system.model.MerchantConfigurationType;
import com.salesmanager.core.business.system.service.EmailService;
import com.salesmanager.core.business.system.service.MerchantConfigurationService;
import com.salesmanager.core.modules.email.EmailConfig;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.ConfigListWrapper;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;


@Controller
public class ConfigurationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);
	
	@Autowired
	private MerchantConfigurationService merchantConfigurationService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	Environment env;
	

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/accounts.html", method=RequestMethod.GET)
	public String displayAccountsConfguration(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		setConfigurationMenu(model, request);
		List<MerchantConfiguration> configs = new ArrayList<MerchantConfiguration>();
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		MerchantConfiguration merchantFBConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_FACEBOOK_PAGE_URL,store);
		if(null == merchantFBConfiguration)
		{
			merchantFBConfiguration = new MerchantConfiguration();
			merchantFBConfiguration.setKey(Constants.KEY_FACEBOOK_PAGE_URL);
			merchantFBConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
		}
		configs.add(merchantFBConfiguration);
		
		MerchantConfiguration merchantGoogleAnalyticsConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_GOOGLE_ANALYTICS_URL,store);
		if(null == merchantGoogleAnalyticsConfiguration)
		{
			merchantGoogleAnalyticsConfiguration = new MerchantConfiguration();
			merchantGoogleAnalyticsConfiguration.setKey(Constants.KEY_GOOGLE_ANALYTICS_URL);
			merchantGoogleAnalyticsConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
		}
		configs.add(merchantGoogleAnalyticsConfiguration);
		
		/**
		MerchantConfiguration merchantGoogleApiConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_GOOGLE_API_KEY,store);
		if(null == merchantGoogleApiConfiguration)
		{
			merchantGoogleApiConfiguration = new MerchantConfiguration();
			merchantGoogleApiConfiguration.setKey(Constants.KEY_GOOGLE_API_KEY);
			merchantGoogleApiConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
		}
		configs.add(merchantGoogleApiConfiguration);
		**/
		
		MerchantConfiguration twitterConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_TWITTER_HANDLE,store);
		if(null == twitterConfiguration)
		{
			twitterConfiguration = new MerchantConfiguration();
			twitterConfiguration.setKey(Constants.KEY_TWITTER_HANDLE);
			twitterConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
		}
		configs.add(twitterConfiguration);
		
		ConfigListWrapper configWrapper = new ConfigListWrapper();
		configWrapper.setMerchantConfigs(configs);
		model.addAttribute("configuration",configWrapper);
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Configuration.accounts;
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/saveConfiguration.html", method=RequestMethod.POST)
	public String saveConfigurations(@ModelAttribute("configuration") ConfigListWrapper configWrapper, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception
	{
		setConfigurationMenu(model, request);
		List<MerchantConfiguration> configs = configWrapper.getMerchantConfigs();
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		for(MerchantConfiguration mConfigs : configs)
		{
			mConfigs.setMerchantStore(store);
			if(!StringUtils.isBlank(mConfigs.getValue())) {
				mConfigs.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
				merchantConfigurationService.saveOrUpdate(mConfigs);
			} else {//remove if submited blank and exists
				MerchantConfiguration config = merchantConfigurationService.getMerchantConfiguration(mConfigs.getKey(), store);
				if(config!=null) {
					merchantConfigurationService.delete(config);
				}
			}
		}	
		model.addAttribute("success","success");
		model.addAttribute("configuration",configWrapper);
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Configuration.accounts;
		
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/email.html", method=RequestMethod.GET)
	public String displayEmailSettings(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setEmailConfigurationMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		EmailConfig emailConfig = emailService.getEmailConfiguration(store);
		if(emailConfig == null){
			emailConfig = new EmailConfig();
			//TODO: Need to check below properties. When there are no record available in MerchantConfguration table with EMAIL_CONFIG key, 
			// instead of showing blank fields in setup screen, show default configured values from email.properties
			emailConfig.setProtocol(env.getProperty("mailSender.protocol"));
			emailConfig.setHost(env.getProperty("mailSender.host"));
			emailConfig.setPort(env.getProperty("mailSender.port}"));
			emailConfig.setUsername(env.getProperty("mailSender.username"));
			emailConfig.setPassword(env.getProperty("mailSender.password"));
			emailConfig.setSmtpAuth(Boolean.parseBoolean(env.getProperty("mailSender.mail.smtp.auth")));
			emailConfig.setStarttls(Boolean.parseBoolean(env.getProperty("mail.smtp.starttls.enable")));
		}
		
		model.addAttribute("configuration", emailConfig);
		return ControllerConstants.Tiles.Configuration.email;
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/configuration/saveEmailConfiguration.html", method=RequestMethod.POST)
	public String saveEmailSettings(@ModelAttribute("configuration") EmailConfig config, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		setEmailConfigurationMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		EmailConfig emailConfig = emailService.getEmailConfiguration(store);
		if(emailConfig == null){
			emailConfig = new EmailConfig();
		}
		
		// populte EmailConfig model from UI values
		emailConfig.setProtocol(config.getProtocol());
		emailConfig.setHost(config.getHost());
		emailConfig.setPort(config.getPort());
		emailConfig.setUsername(config.getUsername());
		emailConfig.setPassword(config.getPassword());
		emailConfig.setSmtpAuth(config.isSmtpAuth());
		emailConfig.setStarttls(config.isStarttls());
		
		emailService.saveEmailConfiguration(emailConfig, store);
		
		model.addAttribute("configuration", emailConfig);
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Configuration.email;
	}
	
	private void setConfigurationMenu(Model model, HttpServletRequest request) throws Exception {
		
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("configuration", "configuration");
		activeMenus.put("accounts-conf", "accounts-conf");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("configuration");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
	}
	
	private void setEmailConfigurationMenu(Model model, HttpServletRequest request) throws Exception {
		
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("configuration", "configuration");
		activeMenus.put("email-conf", "email-conf");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("configuration");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
	}
}
