package com.salesmanager.shop.store.api.v1.payment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.shop.model.system.IntegrationModuleEntity;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Payment api" })
@SwaggerDefinition(tags = {
		@Tag(name = "Payment resources", description = "Payment resources") })
public class PaymentApi {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(PaymentApi.class);
	
		@Autowired
		private PaymentService paymentService;
	
	
	  /**
	   * Get available payment modules
	   * @param merchantStore
	   * @param language
	   * @return
	   */
	  @GetMapping("/private/modules/payment")
	  @ApiOperation(
	      httpMethod = "GET",
	      value = "List list of payment modules",
	      notes = "Requires administration access",
	      produces = "application/json",
	      response = List.class)
	  @ApiImplicitParams({
	      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
	  })
	  public List<IntegrationModuleEntity> paymentModules(
	      @ApiIgnore MerchantStore merchantStore,
	      @ApiIgnore Language language) {

		  try {
			  List<IntegrationModule> modules = paymentService.getPaymentMethods(merchantStore);
			  
			  //configured modules
			  Map<String,IntegrationConfiguration> configuredModules = paymentService.getPaymentModulesConfigured(merchantStore);
			  return modules.stream().map(m -> this.integrationModule(m, configuredModules)).collect(Collectors.toList());

			} catch (ServiceException e) {
				LOGGER.error("Error getting payment modules", e);
				throw new ServiceRuntimeException("Error getting payment modules", e);
			}


	  }
	  
	  private IntegrationModuleEntity integrationModule(IntegrationModule module, Map<String,IntegrationConfiguration> configuredModules) {
		  
		  IntegrationModuleEntity readable = null;
		  readable = new IntegrationModuleEntity();
		  
		  readable.setCode(module.getCode());
		  readable.setImage(module.getImage());
		  if(configuredModules.containsKey(module.getCode())) {
			  IntegrationConfiguration conf = configuredModules.get(module.getCode());
			  readable.setConfigured(true);
		  }
		  return readable;

	  }

}
