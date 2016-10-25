package com.salesmanager.core.modules.utils;

import com.salesmanager.core.model.common.Address;

public interface GeoLocation {
	
	Address getAddress(String ipAddress) throws Exception;

}
