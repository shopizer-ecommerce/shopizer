package com.salesmanager.core.business.modules.cms.impl;

/**
 * Used for managing images
 * @author casams1
 *
 */
public class StoreCacheManagerImpl extends CacheManagerImpl {
	
	
	private final static String NAMED_CACHE = "StoreRepository";


	public StoreCacheManagerImpl(String location) {		
		super.init(NAMED_CACHE,location);
	}



}

