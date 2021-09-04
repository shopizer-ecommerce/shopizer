package com.salesmanager.shop.admin.controller.shipping;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingType;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;

@Controller
public class ShippingPackagingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingPackagingController.class);
	

	@Inject
	private ShippingService shippingService;
	
	@Inject
	LabelUtils messages;
	

	/**
	 * Displays shipping packaging
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/shippingPackaging.html", method=RequestMethod.GET)
	public String displayShippingPackaging(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


		this.setMenu(model, request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
			shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
		}

		model.addAttribute("configuration", shippingConfiguration);
		model.addAttribute("store",store);
		return ControllerConstants.Tiles.Shipping.shippingPackaging;
		
		
	}
	
	/**
	 * Saves shipping packaging
	 * @param configuration
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/saveShippingPackaging.html", method=RequestMethod.POST)
	public String saveShippingPackaging(@ModelAttribute("configuration") ShippingConfiguration configuration, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {


		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get original configuration
		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		String sweight = df.format(configuration.getBoxWeight());
		double weight = Double.parseDouble(sweight);
		
		shippingConfiguration.setBoxHeight(configuration.getBoxHeight());
		shippingConfiguration.setBoxLength(configuration.getBoxLength());
		shippingConfiguration.setBoxWeight(weight);
		shippingConfiguration.setBoxWidth(configuration.getBoxWidth());
		
		shippingConfiguration.setShipPackageType(configuration.getShipPackageType());
		

		shippingService.saveShippingConfiguration(shippingConfiguration, store);
		
		model.addAttribute("configuration", configuration);
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Shipping.shippingPackaging;
		
		
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("shipping", "shipping");
		activeMenus.put("shipping-packages", "shipping-packages");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("shipping");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}


}
