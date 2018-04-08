package com.salesmanager.core.business.modules.cms.impl;


public class LocalCacheManagerImpl {
	
	private static  LocalCacheManagerImpl cacheManager = null;   
	public static LocalCacheManagerImpl getInstance() {
	        
	        if(cacheManager==null) {
	            cacheManager = new LocalCacheManagerImpl();
	        }
	        
	        return cacheManager;
	      
	        
	    }
	

}
