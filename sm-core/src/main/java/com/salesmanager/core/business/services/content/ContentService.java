package com.salesmanager.core.business.services.content;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


/**
 * 
 * Interface defining methods responsible for CMSContentService.
 * ContentServive will be be entry point for CMS and take care of following functionalities.
 * <li>Adding,removing Content images for given merchant store</li>
 * <li>Get,Save,Update Content data for given merchant store</li>
 *  
 * @author Umesh Awasthhi
 * @author Carl Samson
 *
 */
public interface ContentService
    extends SalesManagerEntityService<Long, Content>
{

    List<Content> listByType( ContentType contentType, MerchantStore store, Language language )
        throws ServiceException;

    List<Content> listByType( List<ContentType> contentType, MerchantStore store, Language language )
        throws ServiceException;

    Content getByCode( String code, MerchantStore store )
        throws ServiceException;

    void saveOrUpdate( Content content )
        throws ServiceException;
    
    boolean exists (String code, ContentType type, MerchantStore store);

    Content getByCode( String code, MerchantStore store, Language language )
        throws ServiceException;
    
    Content getById( Long id, MerchantStore store, Language language )
            throws ServiceException;
    
    Content getById( Long id, MerchantStore store)
            throws ServiceException;

    /**
     * Method responsible for storing content file for given Store.Files for given merchant store will be stored in
     * Infinispan.
     * 
     * @param merchantStoreCode merchant store whose content images are being saved.
     * @param contentFile content image being stored
     * @throws ServiceException
     */
    void addContentFile( String merchantStoreCode, InputContentFile contentFile )
        throws ServiceException;

   
    /**
     * Method responsible for storing list of content image for given Store.Images for given merchant store will be stored in
     * Infinispan.
     * 
     * @param merchantStoreCode  merchant store whose content images are being saved.
     * @param contentImagesList list of content images being stored.
     * @throws ServiceException
     */
    void addContentFiles(String merchantStoreCode,List<InputContentFile> contentFilesList) throws ServiceException;
    
    
    /**
     * Method to remove given content image.Images are stored in underlying system based on there name.
     * Name will be used to search given image for removal
     * @param imageContentType
     * @param imageName
     * @param merchantStoreCode merchant store code
     * @throws ServiceException
     */
    void removeFile( String merchantStoreCode, FileContentType fileContentType, String fileName) throws ServiceException;
    
    /**
     * Removes static file
     * FileType is no more important
     * @param storeCode
     * @param filename
     */
    void removeFile(String storeCode, String filename) throws ServiceException;
    
    /**
     * Method to remove all images for a given merchant.It will take merchant store as an input and will
     * remove all images associated with given merchant store.
     * 
     * @param merchantStoreCode
     * @throws ServiceException
     */
    void removeFiles( String merchantStoreCode ) throws ServiceException;
    
    /**
     * Rename file
     * @param merchantStoreCode
     * @param path
     * @param originalName
     * @param newName
     */
    void renameFile( String merchantStoreCode, FileContentType fileContentType, Optional<String> path, String originalName, String newName) throws ServiceException;
    
    /**
     * Method responsible for fetching particular content image for a given merchant store. Requested image will be
     * search in Infinispan tree cache and OutputContentImage will be sent, in case no image is found null will
     * returned.
     * 
     * @param merchantStoreCode
     * @param imageName
     * @return {@link OutputContentImage}
     * @throws ServiceException
     */
    OutputContentFile getContentFile( String merchantStoreCode, FileContentType fileContentType, String fileName )
        throws ServiceException;
    
    
    /**
     * Method to get list of all images associated with a given merchant store.In case of no image method will return an empty list.
     * @param merchantStoreCode
     * @param imageContentType
     * @return list of {@link OutputContentImage}
     * @throws ServiceException
     */
    List<OutputContentFile> getContentFiles( String merchantStoreCode, FileContentType fileContentType )
                    throws ServiceException;

	
    List<String> getContentFilesNames(String merchantStoreCode,
			FileContentType fileContentType) throws ServiceException;

    /**
     * Add the store logo
     * @param merchantStoreCode
     * @param cmsContentImage
     * @throws ServiceException
     */
	void addLogo(String merchantStoreCode, InputContentFile cmsContentImage)
			throws ServiceException;

	/**
	 * Adds a property (option) image
	 * @param merchantStoreId
	 * @param cmsContentImage
	 * @throws ServiceException
	 */
	void addOptionImage(String merchantStoreCode, InputContentFile cmsContentImage)
			throws ServiceException;



	List<Content> listByType(List<ContentType> contentType, MerchantStore store)
			throws ServiceException;
	
	Page<Content> listByType(ContentType contentType, MerchantStore store, int page, int count)
			throws ServiceException;
	
	Page<Content> listByType(ContentType contentType, MerchantStore store, Language language, int page, int count)
			throws ServiceException;

	List<ContentDescription> listNameByType(List<ContentType> contentType,
			MerchantStore store, Language language) throws ServiceException;

	Content getByLanguage(Long id, Language language) throws ServiceException;

	ContentDescription getBySeUrl(MerchantStore store, String seUrl);
	
	/**
	 * Finds content for a specific Merchant for a specific ContentType where content
	 * code is like a given prefix in a specific language
	 * @param type
	 * @param codeLike
	 * @param store
	 * @param lamguage
	 * @return
	 */
	List<Content> getByCodeLike(ContentType type, String codeLike, MerchantStore store, Language language);
	
	void addFolder(MerchantStore store, Optional<String> path, String folderName) throws ServiceException ;
	
	List<String> listFolders(MerchantStore store, Optional<String> path) throws ServiceException ;
	
	void removeFolder(MerchantStore store, Optional<String> path, String folderName) throws ServiceException ;

}
