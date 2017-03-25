package com.salesmanager.admin.controller.taxconfigurations;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.core.business.services.tax.TaxService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.TaxConfiguration;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by umesh on 3/23/17.
 */

@Controller
public class TaxConfigurationController extends AbstractAdminController {

    @Inject
    private TaxService taxService = null;


    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value={"/admin/tax/taxconfiguration/edit.html"}, method= RequestMethod.GET)
    public String displayTaxConfiguration(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxconfiguration", "taxconfiguration");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);


        TaxConfiguration taxConfiguration = taxService.getTaxConfiguration(store);
        if(taxConfiguration == null) {

            taxConfiguration = new TaxConfiguration();

        }

        model.addAttribute("taxConfiguration", taxConfiguration);

        return ControllerConstants.Tiles.Tax.taxConfiguration;
    }

    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value="/admin/tax/taxconfiguration/save.html", method=RequestMethod.POST)
    public String saveTaxConfiguration(@Valid @ModelAttribute("taxConfiguration") TaxConfiguration taxConfiguration, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {


        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxconfiguration", "taxconfiguration");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

        taxService.saveTaxConfiguration(taxConfiguration, store);

        model.addAttribute("success","success");

        return ControllerConstants.Tiles.Tax.taxConfiguration;

    }
}
