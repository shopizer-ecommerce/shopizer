package com.salesmanager.shop.store.controller.store.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.store.PersistableBrand;
import com.salesmanager.shop.model.store.PersistableMerchantStore;
import com.salesmanager.shop.model.store.ReadableBrand;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.model.store.ReadableMerchantStoreList;

/**
 * Layer between shop controllers, services and API with sm-core
 * 
 * @author carlsamson
 *
 */
public interface StoreFacade {

	/**
	 * Find MerchantStore model from store code
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	MerchantStore getByCode(HttpServletRequest request);

	MerchantStore get(String code);

	MerchantStore getByCode(String code);
	
	List<Language> supportedLanguages(MerchantStore store);

	ReadableMerchantStore getByCode(String code, String lang);

	ReadableMerchantStore getFullByCode(String code, String lang);

	ReadableMerchantStoreList findAll(MerchantStoreCriteria criteria, Language language, int page, int count);

	/**
	 * List child stores
	 * 
	 * @param code
	 * @return
	 */
	ReadableMerchantStoreList getChildStores(Language language, String code, int start, int count);

	ReadableMerchantStore getByCode(String code, Language lang);

	ReadableMerchantStore getFullByCode(String code, Language language);

	boolean existByCode(String code);

	/**
	 * List MerchantStore using various criterias
	 * 
	 * @param criteria
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	ReadableMerchantStoreList getByCriteria(MerchantStoreCriteria criteria, Language lang);

	/**
	 * Creates a brand new MerchantStore
	 * 
	 * @param store
	 * @throws Exception
	 */
	//ReadableMerchantStore create(PersistableMerchantStore store);
	void create(PersistableMerchantStore store);

	/**
	 * Updates an existing store
	 * 
	 * @param store
	 * @throws Exception
	 */
	//ReadableMerchantStore update(PersistableMerchantStore store);
	void update(PersistableMerchantStore store);

	/**
	 * Deletes a MerchantStore based on store code
	 * 
	 * @param code
	 */
	void delete(String code);

	/**
	 * Get Logo, social networks and other brand configurations
	 * 
	 * @param code
	 * @return
	 */
	ReadableBrand getBrand(String code);

	/**
	 * Create store brand
	 * 
	 * @param merchantStoreCode
	 * @param brand
	 */
	void createBrand(String merchantStoreCode, PersistableBrand brand);

	/**
	 * Delete store logo
	 */
	void deleteLogo(String code);

	/**
	 * Add MerchantStore logo
	 * 
	 * @param code
	 * @param cmsContentImage
	 */
	void addStoreLogo(String code, InputContentFile cmsContentImage);

	/**
	 * Returns store id, code and name only
	 * 
	 * @return
	 */
	List<ReadableMerchantStore> getMerchantStoreNames(MerchantStoreCriteria criteria);

}
