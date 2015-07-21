package com.salesmanager.core.business.reference.init.service;

import com.salesmanager.core.business.generic.exception.ServiceException;

public interface InitializationDatabase {
	
	boolean isEmpty();
	
	void populate(String name) throws ServiceException;

}
