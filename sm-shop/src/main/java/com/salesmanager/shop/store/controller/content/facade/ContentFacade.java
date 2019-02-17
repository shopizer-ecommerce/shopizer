package com.salesmanager.shop.store.controller.content.facade;

import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.shop.model.content.ContentFile;
import java.util.List;

import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.PersistableContentPage;
import com.salesmanager.shop.model.content.ReadableContent;
import com.salesmanager.shop.model.content.ReadableContentBox;
import com.salesmanager.shop.model.content.ReadableContentPage;

/**
 * Images and files management
 * @author carlsamson
 *
 */
public interface ContentFacade {
	
	
	ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception;
	
	/**
	 * File pth
	 * @param store
	 * @param file
	 * @return
	 */
	String absolutePath(MerchantStore store, String file);
	
	/**
	 * Deletes a file from CMS
	 * @param store
	 * @param fileName
	 */
	void delete(MerchantStore store, String fileName, String fileType);
	
	
	/**
	 * Returns page names and urls configured for a given MerchantStore
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	List<ReadableContentPage> getContentPage(MerchantStore store, Language language);
	
	
	/**
	 * Returns page name by code
	 * @param code
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableContentPage getContentPage(String code, MerchantStore store, Language language);
	

	
	/**
	 * Returns a content box for a given code and merchant store
	 * @param code
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableContentBox getContentBox(String code, MerchantStore store, Language language);
	
	
	/**
	 * Returns content boxes created with code prefix
	 * for example return boxes with code starting with <code>_
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	List<ReadableContentBox> getContentBoxes(ContentType type, String codePrefix, MerchantStore store, Language language);

	void addContentFile(ContentFile file, String merchantStoreCode);
	
	/**
	 * Save content page
	 * @param page
	 * @param merchantStore
	 * @param language
	 */
	void saveContentPage(PersistableContentPage page, MerchantStore merchantStore, Language language);

}
