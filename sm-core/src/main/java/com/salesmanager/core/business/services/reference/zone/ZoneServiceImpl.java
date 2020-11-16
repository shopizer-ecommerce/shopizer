package com.salesmanager.core.business.services.reference.zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.reference.zone.ZoneRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.reference.zone.ZoneDescription;

@Service("zoneService")
public class ZoneServiceImpl extends SalesManagerEntityServiceImpl<Long, Zone> implements
		ZoneService {
	
	private final static String ZONE_CACHE_PREFIX = "ZONES_";

	private ZoneRepository zoneRepository;
	
	@Inject
	private CacheUtils cache;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZoneServiceImpl.class);

	@Inject
	public ZoneServiceImpl(ZoneRepository zoneRepository) {
		super(zoneRepository);
		this.zoneRepository = zoneRepository;
	}

	@Override
	@Cacheable("zoneByCode")
	public Zone getByCode(String code) {
		return zoneRepository.findByCode(code);
	}

	@Override
	public void addDescription(Zone zone, ZoneDescription description) throws ServiceException {
		if (zone.getDescriptions()!=null) {
				if(!zone.getDescriptions().contains(description)) {
					zone.getDescriptions().add(description);
					update(zone);
				}
		} else {
			List<ZoneDescription> descriptions = new ArrayList<ZoneDescription>();
			descriptions.add(description);
			zone.setDescriptons(descriptions);
			update(zone);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Zone> getZones(Country country, Language language) throws ServiceException {
		
		//Validate.notNull(country,"Country cannot be null");
		Validate.notNull(language,"Language cannot be null");
		
		List<Zone> zones = null;
		try {
			
			String countryCode = Constants.DEFAULT_COUNTRY;
			if(country!=null) {
				countryCode = country.getIsoCode();
			}

			String cacheKey = ZONE_CACHE_PREFIX + countryCode + Constants.UNDERSCORE + language.getCode();
			
			zones = (List<Zone>) cache.getFromCache(cacheKey);

		
		
			if(zones==null) {
			
				zones = zoneRepository.listByLanguageAndCountry(countryCode, language.getId());
			
				//set names
				for(Zone zone : zones) {
					ZoneDescription description = zone.getDescriptions().get(0);
					zone.setName(description.getName());
					
				}
				cache.putInCache(zones, cacheKey);
			}

		} catch (Exception e) {
			LOGGER.error("getZones()", e);
		}
		return zones;
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Zone> getZones(String countryCode, Language language) throws ServiceException {
		
		Validate.notNull(countryCode,"countryCode cannot be null");
		Validate.notNull(language,"Language cannot be null");
		
		List<Zone> zones = null;
		try {
			

			String cacheKey = ZONE_CACHE_PREFIX + countryCode + Constants.UNDERSCORE + language.getCode();
			
			zones = (List<Zone>) cache.getFromCache(cacheKey);

		
		
			if(zones==null) {
			
				zones = zoneRepository.listByLanguageAndCountry(countryCode, language.getId());
			
				//set names
				for(Zone zone : zones) {
					ZoneDescription description = zone.getDescriptions().get(0);
					zone.setName(description.getName());
					
				}
				cache.putInCache(zones, cacheKey);
			}

		} catch (Exception e) {
			LOGGER.error("getZones()", e);
		}
		return zones;
		
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Zone> getZones(Language language) throws ServiceException {
		
		Map<String, Zone> zones = null;
		try {

			String cacheKey = ZONE_CACHE_PREFIX + language.getCode();
			
			zones = (Map<String, Zone>) cache.getFromCache(cacheKey);

		
		
			if(zones==null) {
				zones = new HashMap<String, Zone>();
				List<Zone> zns = zoneRepository.listByLanguage(language.getId());
			
				//set names
				for(Zone zone : zns) {
					ZoneDescription description = zone.getDescriptions().get(0);
					zone.setName(description.getName());
					zones.put(zone.getCode(), zone);
					
				}
				cache.putInCache(zones, cacheKey);
			}

		} catch (Exception e) {
			LOGGER.error("getZones()", e);
		}
		return zones;
		
		
	}

}
