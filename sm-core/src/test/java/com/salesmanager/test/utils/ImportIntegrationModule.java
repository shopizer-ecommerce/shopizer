package com.salesmanager.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.loader.IntegrationModulesLoader;
import com.salesmanager.core.business.services.system.ModuleConfigurationService;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.test.configuration.ConfigurationTest;






@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
@Ignore
public class ImportIntegrationModule  {

	@Inject
	private IntegrationModulesLoader integrationModulesLoader;
	
	
	@Inject
	private ModuleConfigurationService moduleCongigurationService;
	
	/**
	 * Import a specific integration module. Will delete and recreate the module
	 * if it already exists 
	 * @throws Exception
	 */
	@Ignore
	//@Test
	public void importSpecificIntegrationModule() throws Exception {
		

			ObjectMapper mapper = new ObjectMapper();
			File file = new File(" /Users/carlsamson/Documents/dev/workspaces/shopizer-master/shopizer/sm-core/src/main/resources/reference/integrationmodules.json");


		try (InputStream in = new FileInputStream(file)) {

			@SuppressWarnings("rawtypes")
			Map[] objects = mapper.readValue(in, Map[].class);

			IntegrationModule module = null;
			//get the module to be loaded
			for (Map o : objects) {
				//load that specific module
				if (o.get("code").equals("beanstream")) {
					//get module object
					module = integrationModulesLoader.loadModule(o);
					break;
				}
			}

			if (module != null) {
				IntegrationModule m = moduleCongigurationService.getByCode(module.getCode());
				if (m != null) {
					moduleCongigurationService.delete(m);
				}

				moduleCongigurationService.create(module);
			}

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}
	
	/**
	 * Import all non existing modules
	 * @throws Exception
	 */
	@Ignore
	//@Test
	public void importNonExistingIntegrationModule() throws Exception {
		

			ObjectMapper mapper = new ObjectMapper();
			File file = new File("/Users/carlsamson/Documents/dev/workspaces/shopizer-master/shopizer/sm-core/src/main/resources/reference/integrationmodules.json");


		try (InputStream in = new FileInputStream(file)) {

			@SuppressWarnings("rawtypes")
			Map[] objects = mapper.readValue(in, Map[].class);


			//get the module to be loaded
			for (Map o : objects) {
				//get module object
				IntegrationModule module = integrationModulesLoader.loadModule(o);

				if (module != null) {
					IntegrationModule m = moduleCongigurationService.getByCode(module.getCode());
					if (m == null) {
						moduleCongigurationService.create(module);
					}
				}

			}


		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}

}
