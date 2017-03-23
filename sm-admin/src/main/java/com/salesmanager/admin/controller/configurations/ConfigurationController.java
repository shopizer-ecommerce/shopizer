package com.salesmanager.admin.controller.configurations;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.ConfigListWrapper;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/21/17.
 */
@Controller
public class ConfigurationController extends AbstractAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);


    @Inject
    private MerchantConfigurationService merchantConfigurationService;

    @Inject
    private EmailService emailService;

    @Inject
    Environment env;

    @PreAuthorize("hasRole('AUTH')")
    @RequestMapping(value="/admin/configuration/accounts.html", method= RequestMethod.GET)
    public String displayAccountsConfguration(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


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

        MerchantConfiguration merchantInstagramConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_INSTAGRAM_URL,store);
        if(null == merchantInstagramConfiguration)
        {
            merchantInstagramConfiguration = new MerchantConfiguration();
            merchantInstagramConfiguration.setKey(Constants.KEY_INSTAGRAM_URL);
            merchantInstagramConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
        }
        configs.add(merchantInstagramConfiguration);

        MerchantConfiguration merchantPinterestConfiguration = merchantConfigurationService.getMerchantConfiguration(Constants.KEY_PINTEREST_PAGE_URL,store);
        if(null == merchantPinterestConfiguration)
        {
            merchantPinterestConfiguration = new MerchantConfiguration();
            merchantPinterestConfiguration.setKey(Constants.KEY_PINTEREST_PAGE_URL);
            merchantPinterestConfiguration.setMerchantConfigurationType(MerchantConfigurationType.CONFIG);
        }
        configs.add(merchantPinterestConfiguration);

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

        return ControllerConstants.Tiles.Configuration.accounts;
    }


    @PreAuthorize("hasRole('AUTH')")
    @RequestMapping(value="/admin/configuration/saveConfiguration.html", method=RequestMethod.POST)
    public String saveConfigurations(@ModelAttribute("configuration") ConfigListWrapper configWrapper, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception
    {
        //setConfigurationMenu(model, request);
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
        return ControllerConstants.Tiles.Configuration.accounts;

    }
}
