package com.salesmanager.core.business.modules.cms.impl;

/**
 * Http server bootstrap
 * 
 * @author carlsamson
 *
 */
public class LocalCacheManagerImpl implements CMSManager {

  private String rootName;// file location root

  public LocalCacheManagerImpl(String rootName) {
    this.rootName = rootName;
  }


  @Override
  public String getRootName() {
    return rootName;
  }

  @Override
  public String getLocation() {
    return "";
  }


}
