package com.salesmanager.admin.controller.tax;

import com.salesmanager.admin.common.utils.LabelUtils;
import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.services.tax.TaxRateService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import com.salesmanager.core.model.tax.taxrate.TaxRateDescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/26/17.
 */
@Controller
public class TaxRatesController extends AbstractAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxRatesController.class);

    private final static char DECIMALCOUNT = '3';


    @Inject
    LabelUtils messages;

    @Inject
    private CountryService countryService;

    @Inject
    private TaxRateService taxRateService;

    @Inject
    private TaxClassService taxClassService;

    @Inject
    private ZoneService zoneService;

    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value={"/admin/tax/taxrates/list.html"}, method= RequestMethod.GET)
    public String displayTaxRates(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxrates", "taxrates");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);

        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

        Language language = (Language)request.getAttribute("LANGUAGE");

        List<Country> countries = countryService.getCountries(language);
        TaxRate taxRate = new TaxRate();
        List<Language> languages = store.getLanguages();

        for(Language l : languages) {

            TaxRateDescription taxRateDescription = new TaxRateDescription();
            taxRateDescription.setLanguage(l);
            taxRate.getDescriptions().add(taxRateDescription);
        }

        taxRate.setMerchantStore(store);
        taxRate.setCountry(store.getCountry());


        List<TaxRate> taxRates = taxRateService.listByStore(store);
        List<TaxClass> taxClasses = taxClassService.listByStore(store);

        model.addAttribute("taxRate", taxRate);
        model.addAttribute("countries", countries);
        model.addAttribute("taxRates", taxRates);
        model.addAttribute("taxClasses", taxClasses);

        return ControllerConstants.Tiles.Tax.taxRates;
    }
}
