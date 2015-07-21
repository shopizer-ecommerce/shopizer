package com.salesmanager.web.services.controller.system;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.system.service.ModuleConfigurationService;

/**
 * Rest services for the system configuration
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping("/services")
public class SystemRESTController {
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemRESTController.class);
	
	@Autowired
	private ModuleConfigurationService moduleConfigurationService;
	
	/**
	 * Creates or updates a configuration module. A JSON has to be created on the client side which represents
	 * an object that will create a new module (payment, shipping ...) which can be used and configured from
	 * the administration tool. Here is an example of configuration accepted
	 * 
	 * 	{
		"module": "PAYMENT",
		"code": "paypal-express-checkout",
		"type":"paypal",
		"version":"104.0",
		"regions": ["*"],
		"image":"icon-paypal.png",
		"configuration":[{"env":"TEST","scheme":"","host":"","port":"","uri":"","config1":"https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="},{"env":"PROD","scheme":"","host":"","port":"","uri":"","config1":"https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="}]

		}
	 *
	 * see : shopizer/sm-core/src/main/resources/reference/integrationmodules.json for more samples
	 * @param json
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value="/private/system/module", method=RequestMethod.POST)
	@ResponseBody
	public void createOrUpdateModule(@RequestBody final String json, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			LOGGER.debug("Creating or updating an integration module : " + json);
			
			moduleConfigurationService.createOrUpdateModule(json);
			
			response.setStatus(200);
			
		} catch(Exception e) {
			response.sendError(503, "Exception while creating or updating the modle " + e.getMessage());
		}


	}

}
