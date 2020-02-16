/**
 * 
 */
package com.salesmanager.core.business.modules.cms.impl;

/**
 * Cache manager to handle static content data in Infinispan cache. static content data can be of
 * following type
 * 
 * <pre>
 * 1. CSS files.
 * 2. JS Files.
 * 3. Digital Data.
 * </pre>
 * 
 * @author Umesh Awasthi
 * @version 1.2
 * 
 *
 */
public class StaticContentCacheManagerImpl extends CacheManagerImpl {

  private final static String NAMED_CACHE = "FilesRepository";

  private String location = null;
  private String root = null;


  public StaticContentCacheManagerImpl(String location, String root) {

    super.init(NAMED_CACHE, location);
    this.location = location;
    this.root = root;
  }

  public String getLocation() {
    return location;
  }

  @Override
  public String getRootName() {
    return root;
  }
}
