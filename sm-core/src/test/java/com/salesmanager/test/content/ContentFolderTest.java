package com.salesmanager.test.content;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Ignore;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Test content with CMS store logo
 * 
 * @author Carl Samson
 *
 */
@Ignore
public class ContentFolderTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {

	@Inject
	private ContentService contentService;


	@Ignore
	public void listImages() {	
	}
	
	@Ignore
	public void addFolder() {
		
		MerchantStore store;
		try {
			store = super.merchantService.getByCode("DEFAULT");

			String folderName = "newFolder";
			
			Optional<String> path = Optional.ofNullable(null);
			
			//add folder to root
			contentService.addFolder(store, path, folderName);
			
			//add new folder to newFolder
		
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@Ignore
	public void listFolders() {
		
	}
	
	@Ignore
	public void removeFolder() {
		
	}

}