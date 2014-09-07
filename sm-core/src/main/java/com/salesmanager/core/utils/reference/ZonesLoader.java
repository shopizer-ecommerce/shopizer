package com.salesmanager.core.utils.reference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.model.ZoneDescription;

@Component
public class ZonesLoader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZonesLoader.class);
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CountryService countryService;
	
	public Map<String, Zone> loadZones(String jsonFilePath) throws Exception {
		
		
		
List<Language> languages = languageService.list();
		
		List<Country> countries = countryService.list();
		Map<String,Country> countriesMap = new HashMap<String,Country>();
		for(Country country : countries) {
			
			countriesMap.put(country.getIsoCode(), country);
			
		}
		
		ObjectMapper mapper = new ObjectMapper();

        try {

              InputStream in =
                    this.getClass().getClassLoader().getResourceAsStream(jsonFilePath);

              @SuppressWarnings("unchecked")
              Map<String,Object> data = mapper.readValue(in, Map.class);
              
              Map<String,Zone> zonesMap = new HashMap<String,Zone>();
              Map<String,List<ZoneDescription>> zonesDescriptionsMap = new HashMap<String,List<ZoneDescription>>();
              Map<String,String> zonesMark = new HashMap<String,String>();
              
              for(Language l : languages) {
	              @SuppressWarnings("rawtypes")
	              List langList = (List)data.get(l.getCode());
	              if(langList!=null) {
		              for(Object z : langList) {
		                    @SuppressWarnings("unchecked")
							Map<String,String> e = (Map<String,String>)z;
		                    String zoneCode = e.get("zoneCode");
		                    ZoneDescription zoneDescription = new ZoneDescription();
		                    zoneDescription.setLanguage(l);
		                    zoneDescription.setName(e.get("zoneName"));
		                    Zone zone = null;
		                    List<ZoneDescription> descriptions = null;
		                    if(!zonesMap.containsKey(zoneCode)) {
		                    	zone = new Zone();
		                    	Country country = countriesMap.get(e.get("countryCode"));
		                    	if(country==null) {
		                    		LOGGER.warn("Country is null for " + zoneCode + " and country code " + e.get("countryCode"));
		                    		continue;
		                    	}
			                    zone.setCountry(country);
		                    	zonesMap.put(zoneCode, zone);
		                    	zone.setCode(zoneCode);

		                    }
		                    
		                    
		                    if(zonesMark.containsKey(l.getCode() + "_" + zoneCode)) {
	                    		LOGGER.warn("This zone seems to be a duplicate !  " + zoneCode + " and language code " + l.getCode());
	                    		continue;
		                    }
		                    
		                    zonesMark.put(l.getCode() + "_" + zoneCode, l.getCode() + "_" + zoneCode);
		                    
		                    if(zonesDescriptionsMap.containsKey(zoneCode)) {
		                    	descriptions = zonesDescriptionsMap.get(zoneCode);
		                    } else {
		                    	descriptions = new ArrayList<ZoneDescription>();
		                    	zonesDescriptionsMap.put(zoneCode, descriptions);
		                    }
		                    
		                    descriptions.add(zoneDescription);

		                }
		             }

              }
              
              
              for (Map.Entry<String, Zone> entry : zonesMap.entrySet()) {
          	    String key = entry.getKey();
          	    Zone value = entry.getValue();
          	    
          	    //if(value.getDescriptions()==null) {
          	    //	LOGGER.warn("This zone " + key + " has no descriptions");
          	    //	continue;
          	    //}

          	    //get descriptions
          	    List<ZoneDescription> descriptions = zonesDescriptionsMap.get(key);
          	    if(descriptions!=null) {
          	    	value.setDescriptons(descriptions);
          	    }
            }

              return zonesMap;
              
  			
  		} catch (Exception e) {
  			throw new ServiceException(e);
  		}
  		
  		

		
	
	
	
	}

}
