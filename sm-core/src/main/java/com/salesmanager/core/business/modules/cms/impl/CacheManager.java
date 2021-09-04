package com.salesmanager.core.business.modules.cms.impl;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.tree.TreeCache;

public interface CacheManager extends CMSManager {

  EmbeddedCacheManager getManager();

  @SuppressWarnings("rawtypes")
  TreeCache getTreeCache();

}
