package com.salesmanager.core.modules.utils;

import com.salesmanager.core.business.common.model.Address;

public interface GeoLocation {
	
	Address getAddress(String ipAddress) throws Exception;

}
