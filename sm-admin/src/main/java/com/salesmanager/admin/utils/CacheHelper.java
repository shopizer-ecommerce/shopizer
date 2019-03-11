package com.salesmanager.admin.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.salesmanager.admin.components.references.MenuLoader;
import com.salesmanager.admin.components.references.ReferencesLoader;
import com.salesmanager.admin.model.references.Country;
import com.salesmanager.admin.model.references.Currency;
import com.salesmanager.admin.model.references.Language;
import com.salesmanager.admin.model.references.MeasureEnum;
import com.salesmanager.admin.model.references.Grouo;
import com.salesmanager.admin.model.references.Reference;
import com.salesmanager.admin.model.references.Zone;
import com.salesmanager.admin.model.web.Menu;


/**
 * Manage all Cache
 * 
 * @author c.samson
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CacheHelper {

  private CacheManager cacheManager;
  @SuppressWarnings("rawtypes")
  private Cache languages;
  @SuppressWarnings("rawtypes")
  private Cache country;
  @SuppressWarnings("rawtypes")
  private Cache currency;
  @SuppressWarnings("rawtypes")
  private Cache menu;
  @SuppressWarnings("rawtypes")
  private Cache zones;
  @SuppressWarnings("rawtypes")
  private Cache weights;
  @SuppressWarnings("rawtypes")
  private Cache sizes;
  private Cache groups;

  @Inject
  ReferencesLoader references;

  @Inject
  private MessagesUtils messages;

  @Inject
  MenuLoader menuLoader;


  @SuppressWarnings("rawtypes")
  private void buildCacheHelper() {


    cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
    cacheManager.init();

    CacheConfiguration<String, ArrayList> noExpirationCacheConfiguration = CacheConfigurationBuilder
        .newCacheConfigurationBuilder(String.class, ArrayList.class, ResourcePoolsBuilder.heap(100))
        .withExpiry(ExpiryPolicy.NO_EXPIRY).build();


    languages = cacheManager.createCache(Constants.Cache.LANGUAGE, noExpirationCacheConfiguration);
    country = cacheManager.createCache(Constants.Cache.COUNTRY, noExpirationCacheConfiguration);
    zones = cacheManager.createCache(Constants.Cache.ZONE, noExpirationCacheConfiguration);
    currency = cacheManager.createCache(Constants.Cache.CURRENCY, noExpirationCacheConfiguration);
    menu = cacheManager.createCache(Constants.Cache.MENU, noExpirationCacheConfiguration);
    weights = cacheManager.createCache(Constants.Cache.WEIGHT, noExpirationCacheConfiguration);
    sizes = cacheManager.createCache(Constants.Cache.SIZE, noExpirationCacheConfiguration);
    groups = cacheManager.createCache(Constants.Cache.GROUPS, noExpirationCacheConfiguration);


  }


  public CacheHelper() {
    buildCacheHelper();
  }

  @SuppressWarnings("unchecked")
  public List<Reference> getMeasures(MeasureEnum measure, Locale locale) throws Exception {

    String isoCode = locale.getLanguage();

    if (MeasureEnum.weights == measure) {

      List<Reference> refs = (List<Reference>) weights.get(isoCode);
      if (CollectionUtils.isEmpty(refs)) {
        Map<MeasureEnum, List<Reference>> r = references.loadMeasures();
        if (r != null) {
          for (MeasureEnum key : r.keySet()) {
            if (key.name().equals(MeasureEnum.weights.name())) {
              List<Reference> someRefs = r.get(key);
              for (Reference aRef : someRefs) {
                String translation =
                    messages.getMessage("label.generic.weightunit." + aRef.getCode(), locale);
                aRef.setName(translation);
              }
              weights.put(isoCode, someRefs);
            }
          }
        }
      }
      return (List<Reference>) weights.get(isoCode);


    }

    if (MeasureEnum.sizes == measure) {

      List<Reference> refs = (List<Reference>) sizes.get(isoCode);
      if (CollectionUtils.isEmpty(refs)) {
        Map<MeasureEnum, List<Reference>> r = references.loadMeasures();
        if (r != null) {
          for (MeasureEnum key : r.keySet()) {
            if (key.name().equals(MeasureEnum.sizes.name())) {
              List<Reference> someRefs = r.get(key);
              for (Reference aRef : someRefs) {
                String translation =
                    messages.getMessage("label.generic.sizeunit." + aRef.getCode(), locale);
                aRef.setName(translation);
              }
              sizes.put(isoCode, someRefs);
            }
          }
        }
      }
      return (List<Reference>) sizes.get(isoCode);

    }

    return null;


  }

  @SuppressWarnings("unchecked")
  public List<Language> getLanguages(Locale locale) throws Exception {

    String isoCode = locale.getLanguage();

    List<Language> langs = (List<Language>) languages.get(isoCode);
    if (CollectionUtils.isEmpty(langs)) {
      langs = references.loadLanguages(locale);
      for (Language lang : langs) {
        Locale l = new Locale(lang.getCode());
        if (l != null) {
          lang.setName(l.getDisplayName());
        }
      }
      languages.put(isoCode, langs);
    }
    return langs;
  }

  @SuppressWarnings("unchecked")
  public List<Country> getCountry(Locale locale) throws Exception {

    String isoCode = locale.getLanguage();

    List<Country> listCountry = (List<Country>) country.get(isoCode);
    if (CollectionUtils.isEmpty(listCountry)) {
      listCountry = references.loadCountry(locale);
      Collections.sort(listCountry, new Comparator<Country>() {
        @Override
        public int compare(Country item, Country t1) {
          String s1 = item.getName();
          String s2 = t1.getName();
          return s1.compareToIgnoreCase(s2);
        }

      });
      country.put(isoCode, listCountry);
    }
    return listCountry;

  }

  @SuppressWarnings("unchecked")
  public List<Zone> getZones(String code, Locale locale) throws Exception {


    List<Zone> listZones = (List<Zone>) zones.get(code);
    if (CollectionUtils.isEmpty(listZones)) {
      listZones = references.loadZones(code, locale);
      if (listZones != null) {
        Collections.sort(listZones, new Comparator<Zone>() {
          @Override
          public int compare(Zone item, Zone t1) {
            String s1 = item.getName();
            String s2 = t1.getName();
            return s1.compareToIgnoreCase(s2);
          }

        });
        zones.put(code, listZones);
      }
    }

    return listZones;

  }

  @SuppressWarnings("unchecked")
  public List<Currency> getCurrency() throws Exception {


    List<Currency> listCurrency = (List<Currency>) currency.get("currency");
    if (CollectionUtils.isEmpty(listCurrency)) {
      listCurrency = references.loadCurrency();
      Collections.sort(listCurrency, new Comparator<Currency>() {
        @Override
        public int compare(Currency item, Currency t1) {
          String s1 = item.getName();
          String s2 = t1.getName();
          return s1.compareToIgnoreCase(s2);
        }

      });
      country.put("currency", listCurrency);
    }
    return listCurrency;

  }
  
  @SuppressWarnings("unchecked")
  public List<Grouo> getGroups(Locale locale) throws Exception {


    List<Grouo> listGroups = (List<Grouo>) groups.get("groups");
    if (CollectionUtils.isEmpty(listGroups)) {
      listGroups = references.loadPermissions(locale);
      Collections.sort(listGroups, new Comparator<Grouo>() {
        @Override
        public int compare(Grouo item, Grouo t1) {
          String s1 = item.getName();
          String s2 = t1.getName();
          return s1.compareToIgnoreCase(s2);
        }

      });
      groups.put("groups", listGroups);
    }
    return listGroups;

  }

  @SuppressWarnings("unchecked")
  public List<Menu> getMenu(Locale locale) throws Exception {

    List<Menu> m = (List<Menu>) menu.get(Constants.Cache.MENU);
    if (m == null) {
      m = menuLoader.loadMenu();
      // menu.put(Constants.Cache.MENU, m);
    }

    return m;

  }

}
