package com.salesmanager.shop.store.controller.content.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.content.ContentFolder;

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
	void delete(MerchantStore store, String fileName, String fileType) throws Exception;

}
