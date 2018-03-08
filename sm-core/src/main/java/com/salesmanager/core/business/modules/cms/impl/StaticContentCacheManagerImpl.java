/**
 * 
 */
package com.salesmanager.core.business.modules.cms.impl;

import com.google.api.client.util.Value;

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
    
	private final static String NAMED_CACHE = "FilesRepository";
    
    @Value(("${config.cms.files.location}"))
    private String location = null;
    

    public StaticContentCacheManagerImpl(String location) {
        
        super.init(NAMED_CACHE,location);
        
        
    }


	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}
