package com.salesmanager.core.business.modules.cms.impl;

/**
 * Http server bootstrap
 * @author carlsamson
 *
 */
public class LocalCacheManagerImpl implements CMSManager {
	
	private static  LocalCacheManagerImpl cacheManager = null;   
	public static LocalCacheManagerImpl getInstance() {
	        
	        if(cacheManager==null) {
	            cacheManager = new LocalCacheManagerImpl();
	        }
	        
	        return cacheManager;
	      
	        
	    }
	@Override
	public String getRootName() {
		return "";
	}
	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
