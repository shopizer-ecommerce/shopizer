/**
 * 
 */
package com.salesmanager.core.business.modules.cms.impl;

/**
 * Cache manager to handle static content data in Infinispan cache.
 * static content data can be of following type
 * <pre>
 * 1. CSS files.
 * 2. JS Files.
 * 3. Digital Data.
 * </pre> 
 * @author Umesh Awasthi
 * @version 1.2
 * 
 *
 */
public class StaticContentCacheManagerImpl extends CacheManagerImpl
{
    private static  StaticContentCacheManagerImpl cacheManager = null;
    private final static String NAMED_CACHE = "FilesRepository";
    

    private StaticContentCacheManagerImpl() {
        
        super.init(NAMED_CACHE);
        
        
    }

   public static StaticContentCacheManagerImpl getInstance() {
        
        if(cacheManager==null) {
            cacheManager = new StaticContentCacheManagerImpl();
        }
        
        return cacheManager;
      
        
    }
}
