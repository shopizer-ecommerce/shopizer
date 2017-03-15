package com.salesmanager.shop.admin.controller.customers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.system.OptinService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.LabelUtils;

@Controller
public class CustomerOptinController {
	@Inject
	private MerchantStoreService merchantService;
	
	@Inject
	private OptinService optinService;
	@Inject
	private LabelUtils messages;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOptinController.class);
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optin/list.html", method=RequestMethod.GET)
	public String displayOptions(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		return ControllerConstants.Tiles.Optin.optinsList;		

	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optin/edit.html", method=RequestMethod.GET)
	public String displayOptionEdit(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOptin(id,request,response,model,locale);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optin/create.html", method=RequestMethod.GET)
	public String displayOptionCreate(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) throws Exception {
		return displayOptin(null,request,response,model,locale);
	}
	
	private String displayOptin(Long optinId, HttpServletRequest request, HttpServletResponse response,Model model,Locale locale) throws Exception {

		
		this.setMenu(model, request);
		List<MerchantStore> stores = merchantService.list();

		Optin optin = new Optin();
		
		if(optinId!=null && optinId!=0) {//edit mode
			
			
			optin = optinService.getById(optinId);
			
			
			if(optin==null) {
				return "redirect:/admin/customers/optin/list.html";
			}
			

		} else {
			
		}
		
		model.addAttribute("merchants", stores);
		model.addAttribute("optin", optin);
		return ControllerConstants.Tiles.Optin.optinDetails;
		
		
	}
		
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optins/save.html", method=RequestMethod.POST)
	public String saveOptin(@Valid @ModelAttribute("optin") Optin optin, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		MerchantStore store = merchantService.getByCode(optin.getMerchant().getCode());
		Optin dbEntity =	null;	

		if(optin.getId() != null && optin.getId() >0) { //edit entry
			
			//get from DB
			dbEntity = optinService.getById(optin.getId());
			
			if(dbEntity==null) {
				return "redirect:/admin/optins/optins.html";
			}
		}
		optin.setMerchant(store);

		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.Optin.optinDetails;
		}

		optinService.save(optin);
		
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Optin.optinDetails;
	}

	
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optins/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageOptins(HttpServletRequest request, HttpServletResponse response) {

		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<Optin> optins = null;
					

				
			optins = optinService.findByMerchant(store.getId());
				
			for(Optin optin : optins) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", optin.getId());
				entry.put("code", optin.getCode());
				entry.put("description", optin.getDescription());
				entry.put("endDate", DateUtil.formatDate(optin.getEndDate()));
				entry.put("startDate", DateUtil.formatDate(optin.getStartDate()));
				entry.put("merchant", optin.getMerchant().getStorename());
				resp.addDataEntry(entry);
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging optins", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
		
	}
	
	

	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		
		activeMenus.put("optin-customers", "optin-customers");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("customer");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/optins/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteOptin(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Long id = Long.parseLong(sid);
			
			Optin entity = optinService.getById(id);

			if(entity==null || entity.getMerchant().getId().intValue()!=store.getId().intValue()) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				
			} else {
				
				optinService.delete(entity);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting optin", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
}
