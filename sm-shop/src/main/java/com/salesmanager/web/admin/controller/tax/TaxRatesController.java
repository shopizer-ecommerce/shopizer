package com.salesmanager.web.admin.controller.tax;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import com.salesmanager.core.business.tax.model.taxrate.TaxRate;
import com.salesmanager.core.business.tax.model.taxrate.TaxRateDescription;
import com.salesmanager.core.business.tax.service.TaxClassService;
import com.salesmanager.core.business.tax.service.TaxRateService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.LabelUtils;


@Controller
public class TaxRatesController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxRatesController.class);
	
	private final static char DECIMALCOUNT = '3';

	
	@Autowired
	LabelUtils messages;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private TaxRateService taxRateService;
	
	@Autowired
	private TaxClassService taxClassService;
	
	@Autowired
	private ZoneService zoneService;
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value={"/admin/tax/taxrates/list.html"}, method=RequestMethod.GET)
	public String displayTaxRates(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model, request);
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
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRates;
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value = "/admin/tax/taxrates/page.html", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String pageTaxRates(HttpServletRequest request,
			HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();


		try {
			
			NumberFormat nf = null;

			
			nf = NumberFormat.getInstance(Locale.US);

			nf.setMaximumFractionDigits(Integer.parseInt(Character
						.toString(DECIMALCOUNT)));
			nf.setMinimumFractionDigits(Integer.parseInt(Character
						.toString(DECIMALCOUNT)));
			
			
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			Language language = (Language)request.getAttribute("LANGUAGE");
			List<TaxRate> taxRates = taxRateService.listByStore(store,language);

			if(taxRates!=null) {
				for (TaxRate rate : taxRates) {

					Map entry = new HashMap ();
					entry.put("taxRateId", String.valueOf(rate.getId()));
					entry.put("code", rate.getCode());
					List<TaxRateDescription> descriptions = rate.getDescriptions();
					String name = "";
					if(descriptions!=null && descriptions.size()>0) {
						TaxRateDescription desc = descriptions.get(0);
						for(TaxRateDescription description : descriptions) {
							if(description.getLanguage().getCode().equals(language.getCode())) {
								desc = description;
								break;
							}
						}
						name = desc.getName();
					}
					
					entry.put("name", name);
					entry.put("priority", rate.getTaxPriority());
					
					entry.put("piggyback", rate.isPiggyback());
					entry.put("country", rate.getCountry().getIsoCode());
					entry.put("taxClass", rate.getTaxClass().getCode());
					
					
					String zoneCode = rate.getStateProvince();
					if(rate.getZone()!=null) {
						zoneCode = rate.getZone().getCode();
					}
					entry.put("zone", zoneCode);
					
					entry.put("rate", nf.format(rate.getTaxRate()));

					
					resp.addDataEntry(entry);

				}
			}

			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging custom weight based", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = resp.toJSONString();

		return returnString;
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxrates/save.html", method=RequestMethod.POST)
	public String saveTaxRate(@Valid @ModelAttribute("taxRate") TaxRate taxRate, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		
		setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		this.validateTaxRate(model, taxRate, result, store, language, locale);
		
		if (result.hasErrors()) {

			return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRates;

		}

		
		taxRateService.create(taxRate);
		
		List<TaxRate> taxRates = taxRateService.listByStore(store);
		
		model.addAttribute("success","success");
		model.addAttribute("taxRates", taxRates);
		
		
		
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRates;
		
	}
	
	
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxrates/update.html", method=RequestMethod.POST)
	public String updateTaxRate(@Valid @ModelAttribute("taxRate") TaxRate taxRate, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		
		
		setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		this.validateTaxRate(model, taxRate, result, store, language, locale);
		
		if (result.hasErrors()) {

			return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRate;

		}

		
		taxRateService.update(taxRate);
		
		List<TaxRate> taxRates = taxRateService.listByStore(store);
		
		model.addAttribute("success","success");
		model.addAttribute("taxRates", taxRates);
		
		
		
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRate;

		
	}
	
	private void validateTaxRate(Model model, TaxRate taxRate, BindingResult result, MerchantStore store, Language language, Locale locale) throws Exception {
		


		List<TaxClass> taxClasses = taxClassService.listByStore(store);
		

		List<Country> countries = countryService.getCountries(language);
		List<TaxRate> taxRates = taxRateService.listByStore(store);
		

		model.addAttribute("countries", countries);
		model.addAttribute("taxRates", taxRates);
		model.addAttribute("taxClasses", taxClasses);

		if(StringUtils.isBlank(taxRate.getRateText())) {
			FieldError error = new FieldError("taxRate","rateText",messages.getMessage("NotEmpty.taxRate.rateText", locale));
			result.addError(error);
		}
		
		//if(taxRate.isPiggyback() && taxRate.getParent()==null) {
			//FieldError error = new FieldError("taxRate","piggyback",messages.getMessage("NotNull.taxRate.parent", locale));
			//result.addError(error);
		//}
		
		try {
			BigDecimal rate = new BigDecimal(taxRate.getRateText());
			taxRate.setTaxRate(rate);
		} catch (Exception e) {
			FieldError error = new FieldError("taxRate","rateText",messages.getMessage("message.invalid.rate", locale));
			result.addError(error);
		}
		
		//check if code exists
		String taxRateCode = taxRate.getCode();
		TaxRate tr = taxRateService.getByCode(taxRateCode, store);
		if(tr!=null) {
			if(tr.getId().longValue()!=taxRate.getId().longValue()){
				FieldError error = new FieldError("taxRate","code",messages.getMessage("NotEmpty.taxRate.unique.code", locale));
				result.addError(error);
			}
		}
		

		if(taxRate.getTaxPriority()==null) {
			taxRate.setTaxPriority(0);
		}

		
		if(taxRate.getZone()!=null) {
			Zone z = zoneService.getById(taxRate.getZone().getId());
			taxRate.setZone(z);
		}
		

		
		Country  c = countryService.getByCode(taxRate.getCountry().getIsoCode());
		
		taxRate.setCountry(c);
		
		List<TaxRateDescription> descriptions = taxRate.getDescriptions();
		for(TaxRateDescription description : descriptions) {
			description.setTaxRate(taxRate);
		}
		
		if(!taxRate.isPiggyback()) {
			taxRate.setParent(null);
		}
		
		return;
		
	}
	

	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxrates/remove.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String removeTaxRate(HttpServletRequest request, Locale locale) throws Exception {
		
		//do not remove super admin
		
		String taxRateId = request.getParameter("taxRateId");

		AjaxResponse resp = new AjaxResponse();
		

		try {
			

			/**
			 * In order to remove a User the logged in ser must be STORE_ADMIN
			 * or SUPER_USER
			 */
			

			if(taxRateId==null){
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			long ltaxRateId;
			try {
				ltaxRateId = Long.parseLong(taxRateId);
			} catch (Exception e) {
				LOGGER.error("Invalid taxRateId " + taxRateId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			TaxRate taxRate = taxRateService.getById(ltaxRateId);
			
			if(taxRate==null) {
				LOGGER.error("Invalid taxRateId " + taxRateId);
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			

			
			
			taxRateService.delete(taxRate);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting tax rate", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
		
	}
	
	@PreAuthorize("hasRole('TAX')")
	@RequestMapping(value="/admin/tax/taxrates/edit.html", method=RequestMethod.GET)
	public String editTaxRate(@ModelAttribute("id") String id, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");

		TaxRate taxRate = null;
		try {
			Long taxRateId = Long.parseLong(id);
			taxRate = taxRateService.getById(taxRateId);
		} catch (Exception e) {
			LOGGER.error("Cannot parse taxRateId " + id);
			return "redirect:/admin/tax/taxrates/list.html";
		}
		
		if(taxRate==null || taxRate.getMerchantStore().getId()!=store.getId()) {
			return "redirect:/admin/tax/taxrates/list.html";
		}
		
		
		NumberFormat nf = null;

		
		nf = NumberFormat.getInstance(Locale.US);

		nf.setMaximumFractionDigits(Integer.parseInt(Character
					.toString(DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character
					.toString(DECIMALCOUNT)));
		
		taxRate.setRateText(nf.format(taxRate.getTaxRate()));
		
		
		
		List<TaxClass> taxClasses = taxClassService.listByStore(store);
		
		

		List<Country> countries = countryService.getCountries(language);
		List<TaxRate> taxRates = taxRateService.listByStore(store);
		

		model.addAttribute("countries", countries);
		model.addAttribute("taxRates", taxRates);
		model.addAttribute("taxClasses", taxClasses);
		
		model.addAttribute("taxRate", taxRate);
		
		return com.salesmanager.web.admin.controller.ControllerConstants.Tiles.Tax.taxRate;
		
		
		
	}

	
	
	
	private void setMenu(Model model, HttpServletRequest request)
	throws Exception {

		// display menu
		Map<String, String> activeMenus = new HashMap<String, String>();
		activeMenus.put("tax", "tax");
		activeMenus.put("taxrates", "taxrates");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) request
				.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("tax");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		//

	}

}
