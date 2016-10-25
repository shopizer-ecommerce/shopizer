package com.salesmanager.core.business.modules.cms.impl;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.tree.TreeCache;
import org.infinispan.tree.TreeCacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CacheManagerImpl implements CacheManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerImpl.class);

	@SuppressWarnings("rawtypes")
	private TreeCache treeCache = null;

	@SuppressWarnings("unchecked")
	protected void init(String namedCache) {
		
		
		try {
			

				 //manager = new DefaultCacheManager(repositoryFileName);
			
				 VendorCacheManager cacheManager =  VendorCacheManager.getInstance();

				 @SuppressWarnings("rawtypes")
				 Cache cache = cacheManager.getManager().getCache(namedCache);
				 cache.getCacheConfiguration().invocationBatching().enabled();
		    
				 TreeCacheFactory f = new TreeCacheFactory();
		    
				 treeCache = f.createTreeCache(cache);
				 
				 cache.start();
	
		         LOGGER.debug("CMS started");



      } catch (Exception e) {
      	LOGGER.error("Error while instantiating CmsImageFileManager",e);
      } finally {
          
      }
		
		
		
		
		
	}
	
	public EmbeddedCacheManager getManager() {
		return VendorCacheManager.getInstance().getManager();
	}

	@SuppressWarnings("rawtypes")
	public TreeCache getTreeCache() {
		return treeCache;
	}
	
	

}
