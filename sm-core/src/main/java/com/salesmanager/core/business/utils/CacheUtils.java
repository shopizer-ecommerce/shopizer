package com.salesmanager.core.business.utils;

import com.salesmanager.core.model.merchant.MerchantStore;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;


@Component("cache")
public class CacheUtils {

  public final static String REFERENCE_CACHE = "REF";

  private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtils.class);

  private final static String KEY_DELIMITER = "_";

  @Inject
  @Qualifier("serviceCache")
  private Cache cache;

  public void putInCache(Object object, String keyName) {
    cache.put(keyName, object);
  }

  public Object getFromCache(String keyName) {
    ValueWrapper vw = cache.get(keyName);
    if (vw != null) {
      return vw.get();
    }
    return null;
  }

  public List<String> getCacheKeys(MerchantStore store) {
    net.sf.ehcache.Cache cacheImpl = (net.sf.ehcache.Cache) cache.getNativeCache();
    List<String> returnKeys = new ArrayList<>();
    for (Object key : cacheImpl.getKeys()) {
      String sKey = (String) key;

      // a key should be <storeId>_<rest of the key>
      int delimiterPosition = sKey.indexOf(KEY_DELIMITER);

      if (delimiterPosition > 0 && Character.isDigit(sKey.charAt(0))) {
        String keyRemaining = sKey.substring(delimiterPosition + 1);
        returnKeys.add(keyRemaining);
      }
    }

    return returnKeys;
  }

  public void shutDownCache() {
  }

  public void removeFromCache(String keyName) {
    cache.evict(keyName);
  }

  public void removeAllFromCache(MerchantStore store) {
    net.sf.ehcache.Cache cacheImpl = (net.sf.ehcache.Cache) cache.getNativeCache();
    for (Object key : cacheImpl.getKeys()) {

      String sKey = (String) key;

      // a key should be <storeId>_<rest of the key>
      int delimiterPosition = sKey.indexOf(KEY_DELIMITER);

      if (delimiterPosition > 0 && Character.isDigit(sKey.charAt(0))) {
        cache.evict(key);
      }
    }
  }
}
