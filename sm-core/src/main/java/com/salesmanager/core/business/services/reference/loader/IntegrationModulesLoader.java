package com.salesmanager.core.business.services.reference.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.ModuleConfig;

@Component
public class IntegrationModulesLoader {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationModulesLoader.class);
	

	public List<IntegrationModule> loadIntegrationModules(String jsonFilePath) throws Exception {
		
		
		List<IntegrationModule> modules = new ArrayList<IntegrationModule>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
            InputStream in =
                this.getClass().getClassLoader().getResourceAsStream(jsonFilePath);
			
            
            @SuppressWarnings("rawtypes")
			Map[] objects = mapper.readValue(in, Map[].class);

			for (Map object : objects) {

				modules.add(this.loadModule(object));
			}
            
            return modules;

  		} catch (Exception e) {
  			throw new ServiceException(e);
  		}
  		
  		

		
	
	
	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IntegrationModule loadModule(Map object) throws Exception {
		
			ObjectMapper mapper = new ObjectMapper();
	    	IntegrationModule module = new IntegrationModule();
	    	module.setModule((String)object.get("module"));
	    	module.setCode((String)object.get("code"));
	    	module.setImage((String)object.get("image"));
	    	
	    	if(object.get("type")!=null) {
	    		module.setType((String)object.get("type"));
	    	}
	    	
	    	if(object.get("customModule")!=null) {
	    		Object o = object.get("customModule");
	    		Boolean b = false;
	    		if(o instanceof Boolean) {
	    			b = (Boolean)object.get("customModule");
	    		} else {
	    			try {
	    				b = Boolean.valueOf((String) object.get("customModule"));
	    			} catch(Exception e) {
	    				LOGGER.error("Cannot cast " + o.getClass() + " tp a boolean value");
	    			}
	    		}
	    		module.setCustomModule(b);
	    	}
	    	//module.setRegions(regions)
	    	if(object.get("details")!=null) {
	    		
	    		Map<String,String> details = (Map<String,String>)object.get("details");
	    		module.setDetails(details);
	    		
	    		//maintain the original json structure
	    		StringBuilder detailsStructure = new StringBuilder();
	    		int count = 0;
	    		detailsStructure.append("{");
	    		for(String key : details.keySet()) {
	    			String jsonKeyString = mapper.writeValueAsString(key);
	    			detailsStructure.append(jsonKeyString);
	    			detailsStructure.append(":");
	    			String jsonValueString = mapper.writeValueAsString(details.get(key));
	    			detailsStructure.append(jsonValueString);
	        		if(count<(details.size()-1)) {
	        			detailsStructure.append(",");
	        		}
	        		count++;
	    		}
	    		detailsStructure.append("}");
	    		module.setConfigDetails(detailsStructure.toString());
	    		
	    	}
	    	
	    	
	    	List confs = (List)object.get("configuration");
	    	
	    	//convert to json
	    	
	    	
	    	
	    	if(confs!=null) {
	    		StringBuilder configString = new StringBuilder();
	    		configString.append("[");
	    		Map<String,ModuleConfig> moduleConfigs = new HashMap<String,ModuleConfig>();
	        	int count=0;
	    		for(Object oo : confs) {
	        		
	        		Map values = (Map)oo;
	        		
	        		String env = (String)values.get("env");
	        		
	        		ModuleConfig config = new ModuleConfig();
	        		config.setScheme((String)values.get("scheme"));
	        		config.setHost((String)values.get("host"));
	        		config.setPort((String)values.get("port"));
	        		config.setUri((String)values.get("uri"));
	        		config.setEnv((String)values.get("env"));
	        		if(values.get("config1") !=null) {
	        			config.setConfig1((String)values.get("config1"));
	        		}
	        		if(values.get("config2") !=null) {
	        			config.setConfig2((String)values.get("config2"));
	        		}
	        		
	        		String jsonConfigString = mapper.writeValueAsString(config);
	        		configString.append(jsonConfigString);
	        		
	        		moduleConfigs.put(env, config);
	        		
	        		if(count<(confs.size()-1)) {
	        			configString.append(",");
	        		}
	        		count++;
	        		
	        		
	        	}
	        	configString.append("]");
	        	module.setConfiguration(configString.toString());
	        	module.setModuleConfigs(moduleConfigs);
	    	}
	    	
	    	List<String> regions = (List<String>)object.get("regions");
	    	if(regions!=null) {
	    		
	
	    		StringBuilder configString = new StringBuilder();
	    		configString.append("[");
	    		int count=0;
	    		for(String region : regions) {
	    			
	    			module.getRegionsSet().add(region);
	    			String jsonConfigString = mapper.writeValueAsString(region);
	    			configString.append(jsonConfigString);
	    			
	        		if(count<(regions.size()-1)) {
	        			configString.append(",");
	        		}
	        		count++;
	
	    		}
	    		configString.append("]");
	    		module.setRegions(configString.toString());
	
	    	}
	    	
	    	return module;
    	
		
	}

}
