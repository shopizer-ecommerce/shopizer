package com.salesmanager.core.business.modules.cms.impl;

/**
 * Infinispan asset manager
 * @author casams1
 *
 */
public class StoreCacheManagerImpl extends CacheManagerImpl {
	
	
	private final static String NAMED_CACHE = "StoreRepository";


	public StoreCacheManagerImpl(String location) {		
		super.init(NAMED_CACHE,location);
	}


	@Override
	public String getRootName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}



}

