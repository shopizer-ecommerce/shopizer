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

}
