package com.salesmanager.core.business.modules.utils;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.modules.utils.GeoLocation;

/**
 * Using Geolite2 City database
 * http://dev.maxmind.com/geoip/geoip2/geolite2/#Databases
 * @author c.samson
 *
 */
public class GeoLocationImpl implements GeoLocation {
	
	private DatabaseReader reader = null;
	private static final Logger LOGGER = LoggerFactory.getLogger( GeoLocationImpl.class );


	@Override
	public Address getAddress(String ipAddress) throws Exception {
		
			if(reader==null) {
					try {
						java.io.InputStream inputFile = GeoLocationImpl.class.getClassLoader().getResourceAsStream("reference/GeoLite2-City.mmdb");
						reader = new DatabaseReader.Builder(inputFile).build();
					} catch(Exception e) {
						LOGGER.error("Cannot instantiate IP database",e);
					}
			}
		
			Address address = new Address();

			try {
			
			CityResponse response = reader.city(InetAddress.getByName(ipAddress));

			address.setCountry(response.getCountry().getIsoCode());
			address.setPostalCode(response.getPostal().getCode());
			address.setZone(response.getMostSpecificSubdivision().getIsoCode());
			address.setCity(response.getCity().getName());
			
			} catch(com.maxmind.geoip2.exception.AddressNotFoundException ne) {
				LOGGER.debug("Address not fount in DB " + ne.getMessage());
			} catch(Exception e) {
				throw new ServiceException(e);
			}

		
			return address;
		
		
	}


}
