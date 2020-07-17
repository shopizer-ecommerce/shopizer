package com.salesmanager.shop.admin.controller.merchant;

import com.salesmanager.core.business.modules.email.Email;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.reference.Size;
import com.salesmanager.shop.admin.model.reference.Weight;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.constants.EmailConstants;
import com.salesmanager.shop.utils.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class MerchantStoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreController.class);

	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private CountryService countryService;

	@Inject
	private ZoneService zoneService;

	@Inject
	private LanguageService languageService;

	@Inject
	private CurrencyService currencyService;

	@Inject
	private UserService userService;

	@Inject
	private LabelUtils messages;

	@Inject
	private EmailService emailService;

	@Inject
	private EmailUtils emailUtils;

	@Inject
	private FilePathUtils filePathUtils;

	private final static String NEW_STORE_TMPL = "email_template_new_store.ftl";

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin/store/list.html", method = RequestMethod.GET)
	public String displayStores(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale)
			throws Exception {

		setMenu(model, request);
		return ControllerConstants.Tiles.Store.stores;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin/store/paging.html", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageStores(HttpServletRequest request, HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();

		try {

			List<MerchantStore> stores = merchantStoreService.findAllStoreCodeNameEmail();

			for (MerchantStore store : stores) {

				if (!store.getCode().equals(MerchantStore.DEFAULT_STORE)) {
					Map<String, String> entry = new HashMap<String, String>();
					entry.put("storeId", String.valueOf(store.getId()));
					entry.put("code", store.getCode());
					entry.put("name", store.getStorename());
					entry.put("email", store.getStoreEmailAddress());
					resp.addDataEntry(entry);
				}

			}

			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = resp.toJSONString();

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString, httpHeaders, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value = "/admin/store/storeCreate.html", method = RequestMethod.GET)
	public String displayMerchantStoreCreate(Model model, HttpServletRequest request, HttpServletResponse response,
			Locale locale) throws Exception {

		setMenu(model, request);

		MerchantStore store = new MerchantStore();

		MerchantStore sessionStore = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
		store.setCurrency(sessionStore.getCurrency());
		store.setCountry(sessionStore.getCountry());
		store.setZone(sessionStore.getZone());
		store.setStorestateprovince(sessionStore.getStorestateprovince());
		store.setLanguages(sessionStore.getLanguages());
		store.setDomainName(sessionStore.getDomainName());

		return displayMerchantStore(store, model, request, response, locale);
	}

	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value = "/admin/store/store.html", method = RequestMethod.GET)
	public String displayMerchantStore(Model model, HttpServletRequest request, HttpServletResponse response,
			Locale locale) throws Exception {

		setMenu(model, request);
		MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
		return displayMerchantStore(store, model, request, response, locale);
	}

	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value = "/admin/store/editStore.html", method = RequestMethod.GET)
	public String displayMerchantStore(@ModelAttribute("id") Integer id, Model model, HttpServletRequest request,
			HttpServletResponse response, Locale locale) throws Exception {

		setMenu(model, request);
		MerchantStore store = merchantStoreService.getById(id);
		return displayMerchantStore(store, model, request, response, locale);
	}

	private String displayMerchantStore(MerchantStore store, Model model, HttpServletRequest request,
			HttpServletResponse response, Locale locale) throws Exception {

		setMenu(model, request);
		Language language = (Language) request.getAttribute("LANGUAGE");
		List<Language> languages = languageService.getLanguages();
		List<Currency> currencies = currencyService.list();
		if (CollectionUtils.isNotEmpty(currencies)) {
			  Collections.sort(currencies, new Comparator<Currency>() {
			      @Override
			      public int compare(final Currency object1, final Currency object2) {
			          return object1.getName().compareTo(object2.getName());
			      }
			  });
			}
		Date dt = store.getInBusinessSince();
		if (dt != null) {
			store.setDateBusinessSince(DateUtil.formatDate(dt));
		} else {
			store.setDateBusinessSince(DateUtil.formatDate(new Date()));
		}

		// get countries
		List<Country> countries = countryService.getCountries(language);

		List<Weight> weights = new ArrayList<Weight>();
		weights.add(new Weight("LB", messages.getMessage("label.generic.weightunit.LB", locale)));
		weights.add(new Weight("KG", messages.getMessage("label.generic.weightunit.KG", locale)));

		List<Size> sizes = new ArrayList<Size>();
		sizes.add(new Size("CM", messages.getMessage("label.generic.sizeunit.CM", locale)));
		sizes.add(new Size("IN", messages.getMessage("label.generic.sizeunit.IN", locale)));

		// display menu

		model.addAttribute("countries", countries);
		model.addAttribute("languages", languages);
		model.addAttribute("currencies", currencies);

		model.addAttribute("weights", weights);
		model.addAttribute("sizes", sizes);
		model.addAttribute("store", store);

		return "admin-store";

	}

	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value = "/admin/store/save.html", method = RequestMethod.POST)
	public String saveMerchantStore(@Valid @ModelAttribute("store") MerchantStore store, BindingResult result,
			Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		setMenu(model, request);
		MerchantStore sessionStore = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);

		if (store.getId() != null) {
			if (store.getId().intValue() != sessionStore.getId().intValue()) {
				return "redirect:/admin/store/store.html";
			}
		}

		Date date = new Date();
		if (!StringUtils.isBlank(store.getDateBusinessSince())) {
			try {
				date = DateUtil.getDate(store.getDateBusinessSince());
				store.setInBusinessSince(date);
			} catch (Exception e) {
				ObjectError error = new ObjectError("dateBusinessSince",
						messages.getMessage("message.invalid.date", locale));
				result.addError(error);
			}
		}

		List<Currency> currencies = currencyService.list();

		Language language = (Language) request.getAttribute("LANGUAGE");
		List<Language> languages = languageService.getLanguages();

		// get countries
		List<Country> countries = countryService.getCountries(language);

		List<Weight> weights = new ArrayList<Weight>();
		weights.add(new Weight("LB", messages.getMessage("label.generic.weightunit.LB", locale)));
		weights.add(new Weight("KG", messages.getMessage("label.generic.weightunit.KG", locale)));

		List<Size> sizes = new ArrayList<Size>();
		sizes.add(new Size("CM", messages.getMessage("label.generic.sizeunit.CM", locale)));
		sizes.add(new Size("IN", messages.getMessage("label.generic.sizeunit.IN", locale)));

		model.addAttribute("weights", weights);
		model.addAttribute("sizes", sizes);

		model.addAttribute("countries", countries);
		model.addAttribute("languages", languages);
		model.addAttribute("currencies", currencies);

		Country c = store.getCountry();
		List<Zone> zonesList = zoneService.getZones(c, language);

		if ((zonesList == null || zonesList.size() == 0) && StringUtils.isBlank(store.getStorestateprovince())) {

			ObjectError error = new ObjectError("zone.code", messages.getMessage("merchant.zone.invalid", locale));
			result.addError(error);

		}

		if (result.hasErrors()) {
			return "admin-store";
		}

		// get country
		Country country = store.getCountry();
		country = countryService.getByCode(country.getIsoCode());
		Zone zone = store.getZone();
		if (zone != null) {
			zone = zoneService.getByCode(zone.getCode());
		}
		Currency currency = store.getCurrency();
		currency = currencyService.getById(currency.getId());

		List<Language> supportedLanguages = store.getLanguages();
		List<Language> supportedLanguagesList = new ArrayList<Language>();
		Map<String, Language> languagesMap = languageService.getLanguagesMap();
		for (Language lang : supportedLanguages) {

			Language l = languagesMap.get(lang.getCode());
			if (l != null) {
				supportedLanguagesList.add(l);
			}

		}

		Language defaultLanguage = store.getDefaultLanguage();
		defaultLanguage = languageService.getById(defaultLanguage.getId());
		if (defaultLanguage != null) {
			store.setDefaultLanguage(defaultLanguage);
		}

		Locale storeLocale = LocaleUtils.getLocale(defaultLanguage);

		store.setStoreTemplate(sessionStore.getStoreTemplate());
		store.setCountry(country);
		store.setZone(zone);
		store.setCurrency(currency);
		store.setDefaultLanguage(defaultLanguage);
		store.setLanguages(supportedLanguagesList);
		store.setLanguages(supportedLanguagesList);

		merchantStoreService.saveOrUpdate(store);

		if (!store.getCode().equals(sessionStore.getCode())) {// create store
			// send email

			try {

				Map<String, String> templateTokens = emailUtils.createEmailObjectsMap(request.getContextPath(), store,
						messages, storeLocale);
				templateTokens.put(EmailConstants.EMAIL_NEW_STORE_TEXT,
						messages.getMessage("email.newstore.text", storeLocale));
				templateTokens.put(EmailConstants.EMAIL_STORE_NAME,
						messages.getMessage("email.newstore.name", new String[] { store.getStorename() }, storeLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_STORE_INFO_LABEL,
						messages.getMessage("email.newstore.info", storeLocale));

				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL_LABEL,
						messages.getMessage("label.adminurl", storeLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL, filePathUtils.buildAdminUri(store, request));

				Email email = new Email();
				email.setFrom(store.getStorename());
				email.setFromEmail(store.getStoreEmailAddress());
				email.setSubject(messages.getMessage("email.newstore.title", storeLocale));
				email.setTo(store.getStoreEmailAddress());
				email.setTemplateName(NEW_STORE_TMPL);
				email.setTemplateTokens(templateTokens);

				emailService.sendHtmlEmail(store, email);

			} catch (Exception e) {
				LOGGER.error("Cannot send email to user", e);
			}

		}

		sessionStore = merchantStoreService.getByCode(sessionStore.getCode());

		// update session store
		request.getSession().setAttribute(Constants.ADMIN_STORE, sessionStore);

		model.addAttribute("success", "success");
		model.addAttribute("store", store);

		return "admin-store";
	}

	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value = "/admin/store/checkStoreCode.html", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> checkStoreCode(HttpServletRequest request, HttpServletResponse response,
			Locale locale) {
		String code = request.getParameter("code");

		AjaxResponse resp = new AjaxResponse();

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		try {

			if (StringUtils.isBlank(code)) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				return new ResponseEntity<String>(resp.toJSONString(), httpHeaders, HttpStatus.OK);
			}

			MerchantStore store = merchantStoreService.getByCode(code);

			if (store != null) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				return new ResponseEntity<String>(resp.toJSONString(), httpHeaders, HttpStatus.OK);
			}

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while getting user", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}

		String returnString = resp.toJSONString();

		return new ResponseEntity<String>(returnString, httpHeaders, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin/store/remove.html", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeMerchantStore(HttpServletRequest request, Locale locale)
			throws Exception {

		String sMerchantStoreId = request.getParameter("storeId");

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		try {

			Integer storeId = Integer.parseInt(sMerchantStoreId);
			MerchantStore store = merchantStoreService.getById(storeId);

			User user = userService.getByUserName(request.getRemoteUser());

			/**
			 * In order to remove a Store the logged in ser must be SUPERADMIN
			 */

			// check if the user removed has group SUPERADMIN
			boolean isSuperAdmin = false;
			if (UserUtils.userInGroup(user, Constants.GROUP_SUPERADMIN)) {
				isSuperAdmin = true;
			}

			if (!isSuperAdmin) {
				resp.setStatusMessage(messages.getMessage("message.security.caanotremovesuperadmin", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString, httpHeaders, HttpStatus.OK);
			}

			merchantStoreService.delete(store);

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while deleting product price", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}

		String returnString = resp.toJSONString();

		return new ResponseEntity<String>(returnString, httpHeaders, HttpStatus.OK);

	}

	private void setMenu(Model model, HttpServletRequest request) throws Exception {

		// display menu
		Map<String, String> activeMenus = new HashMap<String, String>();
		activeMenus.put("store", "store");
		activeMenus.put("storeDetails", "storeDetails");

		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) request.getAttribute("MENUMAP");

		Menu currentMenu = (Menu) menus.get("store");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		//

	}

}
