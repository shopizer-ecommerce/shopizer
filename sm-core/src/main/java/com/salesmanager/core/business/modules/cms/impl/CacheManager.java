package com.salesmanager.core.business.modules.cms.impl;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.tree.TreeCache;

public interface CacheManager extends CMSManager {

  public EmbeddedCacheManager getManager();

  @SuppressWarnings("rawtypes")
  public TreeCache getTreeCache();

}
