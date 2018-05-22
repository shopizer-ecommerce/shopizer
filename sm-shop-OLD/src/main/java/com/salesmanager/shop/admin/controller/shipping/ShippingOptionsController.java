package com.salesmanager.shop.admin.controller.shipping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingType;
import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;

@Controller
public class ShippingOptionsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingOptionsController.class);
	

	@Inject
	private ShippingService shippingService;
	
	@Inject
	LabelUtils messages;
	
	@Inject
	private ProductPriceUtils priceUtil;
	
	/**
	 * Displays shipping options
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/shippingOptions.html", method=RequestMethod.GET)
	public String displayShippingOptions(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


		this.setMenu(model, request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		

		
		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
			shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
		}
		
		if(shippingConfiguration!=null) {
			
			if(shippingConfiguration.getHandlingFees()!=null) {
				shippingConfiguration.setHandlingFeesText(priceUtil.getAdminFormatedAmount(store,shippingConfiguration.getHandlingFees()));
			}
			
			if(shippingConfiguration.getOrderTotalFreeShipping()!=null) {
				shippingConfiguration.setOrderTotalFreeShippingText(priceUtil.getAdminFormatedAmount(store,shippingConfiguration.getOrderTotalFreeShipping()));
			}
			
		}
		

		model.addAttribute("configuration", shippingConfiguration);
		return ControllerConstants.Tiles.Shipping.shippingOptions;
		
		
	}
	
	/**
	 * Saves shipping options
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
	@RequestMapping(value="/admin/shipping/saveShippingOptions.html", method=RequestMethod.POST)
	public String saveShippingOptions(@ModelAttribute("configuration") ShippingConfiguration configuration, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {


		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get original configuration
		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
		}
		
		BigDecimal submitedOrderPrice = null;
		if(!StringUtils.isBlank(configuration.getOrderTotalFreeShippingText())){
			try {
				submitedOrderPrice = priceUtil.getAmount(configuration.getOrderTotalFreeShippingText());
				shippingConfiguration.setOrderTotalFreeShipping(submitedOrderPrice);
			} catch (Exception e) {
				ObjectError error = new ObjectError("orderTotalFreeShippingText",messages.getMessage("message.invalid.price", locale));
				result.addError(error);
			}
		}
		
		BigDecimal submitedHandlingPrice = null;
		if(!StringUtils.isBlank(configuration.getHandlingFeesText())){
			try {
				submitedHandlingPrice = priceUtil.getAmount(configuration.getHandlingFeesText());
				shippingConfiguration.setHandlingFees(submitedHandlingPrice);
			} catch (Exception e) {
				ObjectError error = new ObjectError("handlingFeesText",messages.getMessage("message.invalid.price", locale));
				result.addError(error);
			}
		}
		
		shippingConfiguration.setFreeShippingEnabled(configuration.isFreeShippingEnabled());
		shippingConfiguration.setTaxOnShipping(configuration.isTaxOnShipping());
		if(configuration.getShipFreeType()!=null) {
			shippingConfiguration.setShipFreeType(configuration.getShipFreeType());
		}
		shippingConfiguration.setShipOptionPriceType(configuration.getShipOptionPriceType());

		shippingService.saveShippingConfiguration(shippingConfiguration, store);
		
		model.addAttribute("configuration", configuration);
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Shipping.shippingOptions;
		
		
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("shipping", "shipping");
		activeMenus.put("shipping-options", "shipping-options");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("shipping");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}


}
