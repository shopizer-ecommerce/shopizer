package com.salesmanager.shop.store.api.v1.payment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.shop.model.system.IntegrationModuleConfiguration;
import com.salesmanager.shop.model.system.IntegrationModuleSummaryEntity;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This API is for payment modules configurations. For payment of orders see
 * order
 * 
 * @author carlsamson
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Payment api" })
@SwaggerDefinition(tags = { @Tag(name = "Payment management resources", description = "Payment management resources") })
public class PaymentApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentApi.class);

	@Autowired
	private PaymentService paymentService;

	/**
	 * Get available payment modules
	 * 
	 * @param merchantStore
	 * @param language
	 * @return
	 */
	@GetMapping("/private/modules/payment")
	@ApiOperation(httpMethod = "GET", value = "List list of payment modules", notes = "Requires administration access", produces = "application/json", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
	public List<IntegrationModuleSummaryEntity> paymentModules(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		try {
			List<IntegrationModule> modules = paymentService.getPaymentMethods(merchantStore);

			// configured modules
			Map<String, IntegrationConfiguration> configuredModules = paymentService
					.getPaymentModulesConfigured(merchantStore);
			return modules.stream().map(m -> integrationModule(m, configuredModules)).collect(Collectors.toList());

		} catch (ServiceException e) {
			LOGGER.error("Error getting payment modules", e);
			throw new ServiceRuntimeException("Error getting payment modules", e);
		}

	}

	@PostMapping(value = "/private/modules/payment")
	public void configure(
			@RequestBody IntegrationModuleConfiguration configuration,
			@ApiIgnore MerchantStore merchantStore) {
		
		try {
			
			List<IntegrationModule> modules = paymentService.getPaymentMethods(merchantStore);
			
		    Map<String, IntegrationModule> map = modules.stream()
		    	      .collect(Collectors.toMap(IntegrationModule::getCode, module -> module));
		    
		    IntegrationModule config = map.get(configuration.getCode());

			if (config == null) {
				throw new ResourceNotFoundException("Payment module [" + configuration.getCode() + "] not found");
			}
			
			Map<String, IntegrationConfiguration> configuredModules = paymentService
					.getPaymentModulesConfigured(merchantStore);
			
			IntegrationConfiguration integrationConfiguration = configuredModules.get(configuration.getCode());
			
			if(integrationConfiguration == null) {
				integrationConfiguration = new IntegrationConfiguration();
				integrationConfiguration.setModuleCode(configuration.getCode());
			}


			integrationConfiguration.setActive(configuration.isActive());
			integrationConfiguration.setDefaultSelected(configuration.isDefaultSelected());
			integrationConfiguration.setIntegrationKeys(configuration.getIntegrationKeys());
			integrationConfiguration.setIntegrationOptions(configuration.getIntegrationOptions());

			
			paymentService.savePaymentModuleConfiguration(integrationConfiguration, merchantStore);
		} catch (ServiceException e) {
			LOGGER.error("Error getting payment modules", e);
			throw new ServiceRuntimeException("Error saving payment module", e);
		}

	}

	/**
	 * Get merchant payment module details
	 * 
	 * @param code
	 * @param merchantStore
	 * @param language
	 * @return
	 */
	@GetMapping("/private/modules/payment/{code}")
	@ApiOperation(httpMethod = "GET", value = "Payment module by code", produces = "application/json", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
	public IntegrationModuleConfiguration paymentModule(@PathVariable String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		try {
			
			// get module
			IntegrationModule integrationModule = paymentService.getPaymentMethodByCode(merchantStore, code);
			if (integrationModule == null) {
				throw new ResourceNotFoundException("Payment module [" + code + "] not found");
			}
			
			IntegrationModuleConfiguration returnConfig = new IntegrationModuleConfiguration();
			returnConfig.setConfigurable(integrationModule.getConfigurable());
			returnConfig.setActive(false);
			returnConfig.setDefaultSelected(false);
			returnConfig.setCode(code);
			
			

			// configured modules
			IntegrationConfiguration config = paymentService.getPaymentConfiguration(code, merchantStore);
			
			if(config == null) {
				return returnConfig;
			}


			/**
			 * Build return object for now this is a read copy
			 */

			
			returnConfig.setActive(config.isActive());
			returnConfig.setDefaultSelected(config.isDefaultSelected());
			returnConfig.setCode(code);
			returnConfig.setIntegrationKeys(config.getIntegrationKeys());
			returnConfig.setIntegrationOptions(config.getIntegrationOptions());

			return returnConfig;

		} catch (ServiceException e) {
			LOGGER.error("Error getting payment module [" + code + "]", e);
			throw new ServiceRuntimeException("Error getting payment module [" + code + "]", e);
		}

	}

	private IntegrationModuleSummaryEntity integrationModule(IntegrationModule module,
			Map<String, IntegrationConfiguration> configuredModules) {

		IntegrationModuleSummaryEntity readable = null;
		readable = new IntegrationModuleSummaryEntity();

		readable.setCode(module.getCode());
		readable.setImage(module.getImage());
		readable.setBinaryImage(module.getBinaryImage());
		//readable.setRequiredKeys(module.getConfigurables());
		readable.setConfigurable(module.getConfigurable());
		if (configuredModules.containsKey(module.getCode())) {
			readable.setConfigured(true);
			if(configuredModules.get(module.getCode()).isActive()) {
				readable.setActive(true);
			}
		}
		return readable;

	}

}
