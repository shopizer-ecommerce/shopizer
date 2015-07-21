package com.salesmanager.core.modules.utils;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.salesmanager.core.business.common.model.Address;


public class GeoLocationImpl implements GeoLocation {
	
	private DatabaseReader reader = null;
	private static final Logger LOGGER = LoggerFactory.getLogger( GeoLocationImpl.class );
	//@Value("${dbPath:classpath:/reference/GeoLite2-Country.mmdb}")
	//private Resource db;

	


	@Override
	public Address getAddress(String ipAddress) throws Exception {
		
			if(reader==null) {
				//if(db!=null) {
					//File file = db.getFile();
					try {
						java.io.InputStream inputFile = GeoLocationImpl.class.getClassLoader().getResourceAsStream("reference/GeoLite2-Country.mmdb");
						reader = new DatabaseReader.Builder(inputFile).build();
					} catch(Exception e) {
						LOGGER.error("Cannot instantiate IP database",e);
					}
				//}
			}
		
			Address address = new Address();

			
			CityResponse response = reader.city(InetAddress.getByName(ipAddress));

			address.setCountry(response.getCountry().getIsoCode());
			address.setPostalCode(response.getPostal().getCode());
			address.setZone(response.getMostSpecificSubdivision().getIsoCode());
			address.setCity(response.getCity().getName());
			


		
			return address;
		
		
	}


}
