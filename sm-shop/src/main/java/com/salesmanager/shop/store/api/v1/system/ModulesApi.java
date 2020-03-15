package com.salesmanager.shop.store.api.v1.system;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.services.system.ModuleConfigurationService;

@RestController
@RequestMapping("/api/v1/system")
public class ModulesApi {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ModulesApi.class);
  
  @Inject
  private ModuleConfigurationService moduleConfigurationService;
  
  /**
   * Creates or updates a configuration module. A JSON has to be created on the client side which represents
   * an object that will create a new module (payment, shipping ...) which can be used and configured from
   * the administration tool. Here is an example of configuration accepted
   * 
   *  {
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
/*  @PostMapping(value = "/module", consumes = MediaType.TEXT_PLAIN)
  @ApiOperation(
      httpMethod = "POST",
      value = "Creates a new module",
      notes = "",
      produces = "application/json")
  public ReadableEntity createModule(@RequestBody String json, HttpServletRequest request) {

      LOGGER.debug("Creating an integration module : " + json);
          
      try {
        moduleConfigurationService.createOrUpdateModule(json);
      } catch (ServiceException e) {
        // TODO Auto-generated catch block
        throw new RestApiException(e);
      }

      return new ReadableEntity();

  }*/

}
