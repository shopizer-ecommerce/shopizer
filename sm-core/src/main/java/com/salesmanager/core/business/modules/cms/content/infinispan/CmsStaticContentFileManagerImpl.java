/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content.infinispan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.infinispan.tree.Fqn;
import org.infinispan.tree.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.ContentAssetsManager;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.business.modules.cms.impl.CacheManager;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

/**
 * Manages - Images - Files (js, pdf, css...) on infinispan
 * 
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public class CmsStaticContentFileManagerImpl
		// implements FilePut,FileGet,FileRemove
		implements ContentAssetsManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(CmsStaticContentFileManagerImpl.class);
	private static CmsStaticContentFileManagerImpl fileManager = null;
	private static final String ROOT_NAME = "static-merchant-";

	private String rootName = ROOT_NAME;

	private CacheManager cacheManager;

	public void stopFileManager() {

		try {
			cacheManager.getManager().stop();
			LOGGER.info("Stopping CMS");
		} catch (final Exception e) {
			LOGGER.error("Error while stopping CmsStaticContentFileManager", e);
		}
	}

	@PostConstruct
	void init() {

		this.rootName = cacheManager.getRootName();
		LOGGER.info("init " + getClass().getName() + " setting root" + this.rootName);

	}

	public static CmsStaticContentFileManagerImpl getInstance() {

		if (fileManager == null) {
			fileManager = new CmsStaticContentFileManagerImpl();
		}

		return fileManager;

	}

	/**
	 * <p>
	 * Method to add static content data for given merchant.Static content data
	 * can be of following type
	 * 
	 * <pre>
	 * 1. CSS and JS files
	 * 2. Digital Data like audio or video
	 * </pre>
	 * </p>
	 * <p>
	 * Merchant store code will be used to create cache node where merchant data
	 * will be stored,input data will contain name, file as well type of data
	 * being stored.
	 * 
	 * @see FileContentType
	 *      </p>
	 * 
	 * @param merchantStoreCode
	 *            merchant store for whom data is being stored
	 * @param inputStaticContentData
	 *            data object being stored
	 * @throws ServiceException
	 * 
	 */
	@Override
	public void addFile(final String merchantStoreCode, Optional<String>path, final InputContentFile inputStaticContentData)
			throws ServiceException {
		if (cacheManager.getTreeCache() == null) {
			LOGGER.error("Unable to find cacheManager.getTreeCache() in Infinispan..");
			throw new ServiceException(
					"CmsStaticContentFileManagerInfinispanImpl has a null cacheManager.getTreeCache()");
		}
		try {

			String nodePath = this.getNodePath(merchantStoreCode, inputStaticContentData.getFileContentType());

			final Node<String, Object> merchantNode = this.getNode(nodePath);

			merchantNode.put(inputStaticContentData.getFileName(),
					IOUtils.toByteArray(inputStaticContentData.getFile()));

			LOGGER.info("Content data added successfully.");
		} catch (final Exception e) {
			LOGGER.error("Error while saving static content data", e);
			throw new ServiceException(e);

		}

	}

	/**
	 * <p>
	 * Method to add files for given store.Files will be stored in Infinispan
	 * and will be retrieved based on the storeID. Following steps will be
	 * performed to store static content files
	 * </p>
	 * <li>Merchant Node will be retrieved from the cacheTree if it exists else
	 * new node will be created.</li>
	 * <li>Files will be stored in StaticContentCacheAttribute , which
	 * eventually will be stored in Infinispan</li>
	 * 
	 * @param merchantStoreCode
	 *            Merchant store for which files are getting stored in
	 *            Infinispan.
	 * @param inputStaticContentDataList
	 *            input static content file list which will get
	 *            {@link InputContentImage} stored
	 * @throws ServiceException
	 *             if content file storing process fail.
	 * @see InputStaticContentData
	 * @see StaticContentCacheAttribute
	 */
	@Override
	public void addFiles(final String merchantStoreCode, Optional<String> path, final List<InputContentFile> inputStaticContentDataList)
			throws ServiceException {
		if (cacheManager.getTreeCache() == null) {
			LOGGER.error("Unable to find cacheManager.getTreeCache() in Infinispan..");
			throw new ServiceException(
					"CmsStaticContentFileManagerInfinispanImpl has a null cacheManager.getTreeCache()");
		}
		try {

			for (final InputContentFile inputStaticContentData : inputStaticContentDataList) {

				String nodePath = this.getNodePath(merchantStoreCode, inputStaticContentData.getFileContentType());
				final Node<String, Object> merchantNode = this.getNode(nodePath);
				merchantNode.put(inputStaticContentData.getFileName(),
						IOUtils.toByteArray(inputStaticContentData.getFile()));

			}

			LOGGER.info("Total {} files added successfully.", inputStaticContentDataList.size());

		} catch (final Exception e) {
			LOGGER.error("Error while saving content image", e);
			throw new ServiceException(e);

		}
	}

	/**
	 * Method to return static data for given Merchant store based on the file
	 * name. Content data will be searched in underlying Infinispan cache tree
	 * and {@link OutputStaticContentData} will be returned on finding an
	 * associated file. In case of no file, null be returned.
	 * 
	 * @param store
	 *            Merchant store
	 * @param contentFileName
	 *            name of file being requested
	 * @return {@link OutputStaticContentData}
	 * @throws ServiceException
	 */
	@Override
	public OutputContentFile getFile(final String merchantStoreCode, Optional<String> path, final FileContentType fileContentType,
			final String contentFileName) throws ServiceException {

		if (cacheManager.getTreeCache() == null) {
			throw new ServiceException("CmsStaticContentFileManagerInfinispan has a null cacheManager.getTreeCache()");
		}
		OutputContentFile outputStaticContentData = new OutputContentFile();
		InputStream input = null;
		try {

			String nodePath = this.getNodePath(merchantStoreCode, fileContentType);

			final Node<String, Object> merchantNode = this.getNode(nodePath);

			final byte[] fileBytes = (byte[]) merchantNode.get(contentFileName);

			if (fileBytes == null) {
				LOGGER.warn("file byte is null, no file found");
				return null;
			}

			input = new ByteArrayInputStream(fileBytes);

			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(input, output);

			outputStaticContentData.setFile(output);
			outputStaticContentData.setMimeType(URLConnection.getFileNameMap().getContentTypeFor(contentFileName));
			outputStaticContentData.setFileName(contentFileName);
			outputStaticContentData.setFileContentType(fileContentType);

		} catch (final Exception e) {
			LOGGER.error("Error while fetching file for {} merchant ", merchantStoreCode);
			throw new ServiceException(e);
		}
		return outputStaticContentData;
	}

	@Override
	public List<OutputContentFile> getFiles(final String merchantStoreCode, Optional<String> path, final FileContentType staticContentType)
			throws ServiceException {

		if (cacheManager.getTreeCache() == null) {
			throw new ServiceException("CmsStaticContentFileManagerInfinispan has a null cacheManager.getTreeCache()");
		}
		List<OutputContentFile> images = new ArrayList<OutputContentFile>();
		try {

			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String nodePath = this.getNodePath(merchantStoreCode, staticContentType);

			final Node<String, Object> merchantNode = this.getNode(nodePath);

			for (String key : merchantNode.getKeys()) {

				byte[] imageBytes = (byte[]) merchantNode.get(key);

				OutputContentFile contentImage = new OutputContentFile();

				InputStream input = new ByteArrayInputStream(imageBytes);
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				IOUtils.copy(input, output);

				String contentType = fileNameMap.getContentTypeFor(key);

				contentImage.setFile(output);
				contentImage.setMimeType(contentType);
				contentImage.setFileName(key);

				images.add(contentImage);

			}

		} catch (final Exception e) {
			LOGGER.error("Error while fetching file for {} merchant ", merchantStoreCode);
			throw new ServiceException(e);
		}

		return images;

	}

	@Override
	public void removeFile(final String merchantStoreCode, final FileContentType staticContentType,
			final String fileName, Optional<String> path) throws ServiceException {

		if (cacheManager.getTreeCache() == null) {
			throw new ServiceException("CmsStaticContentFileManagerInfinispan has a null cacheManager.getTreeCache()");
		}

		try {

			String nodePath = this.getNodePath(merchantStoreCode, staticContentType);
			final Node<String, Object> merchantNode = this.getNode(nodePath);

			merchantNode.remove(fileName);

		} catch (final Exception e) {
			LOGGER.error("Error while fetching file for {} merchant ", merchantStoreCode);
			throw new ServiceException(e);
		}

	}

	/**
	 * Removes the data in a given merchant node
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeFiles(final String merchantStoreCode, Optional<String> path) throws ServiceException {

		LOGGER.info("Removing all images for {} merchant ", merchantStoreCode);
		if (cacheManager.getTreeCache() == null) {
			LOGGER.error("Unable to find cacheManager.getTreeCache() in Infinispan..");
			throw new ServiceException("CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()");
		}

		try {

			final StringBuilder merchantPath = new StringBuilder();
			merchantPath.append(getRootName()).append(merchantStoreCode);
			cacheManager.getTreeCache().getRoot().remove(merchantPath.toString());

		} catch (final Exception e) {
			LOGGER.error("Error while deleting content image for {} merchant ", merchantStoreCode);
			throw new ServiceException(e);
		}

	}

	@SuppressWarnings({ "unchecked" })
	private Node<String, Object> getNode(final String node) {
		LOGGER.debug("Fetching node for store {} from Infinispan", node);
		final StringBuilder merchantPath = new StringBuilder();
		merchantPath.append(getRootName()).append(node);

		Fqn contentFilesFqn = Fqn.fromString(merchantPath.toString());

		Node<String, Object> nd = cacheManager.getTreeCache().getRoot().getChild(contentFilesFqn);

		if (nd == null) {

			cacheManager.getTreeCache().getRoot().addChild(contentFilesFqn);
			nd = cacheManager.getTreeCache().getRoot().getChild(contentFilesFqn);

		}

		return nd;

	}

	private String getNodePath(final String storeCode, final FileContentType contentType) {

		StringBuilder nodePath = new StringBuilder();
		nodePath.append(storeCode).append("/").append(contentType.name());

		return nodePath.toString();

	}
	
	
	/**
	 * Returns a folder path so it can be used as base node
	 * @param storeCode
	 * @param folder
	 * @return
	 */
	private String getFolder(final String storeCode, String folder) {

/*		StringBuilder nodePath = new StringBuilder();
		nodePath.append(storeCode).append("/").append(contentType.name());

		return nodePath.toString();*/
		
		
		return null;

	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * Queries the CMS to retrieve all static content files. Only the name of
	 * the file will be returned to the client
	 * 
	 * @param merchantStoreCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<String> getFileNames(final String merchantStoreCode, Optional<String> path, final FileContentType staticContentType)
			throws ServiceException {

		if (cacheManager.getTreeCache() == null) {
			throw new ServiceException("CmsStaticContentFileManagerInfinispan has a null cacheManager.getTreeCache()");
		}

		try {

			String nodePath = this.getNodePath(merchantStoreCode, staticContentType);
			final Node<String, Object> objectNode = this.getNode(nodePath);

			if (objectNode.getKeys().isEmpty()) {
				LOGGER.warn("Unable to find content attribute for given merchant");
				return Collections.emptyList();
			}
			return new ArrayList<String>(objectNode.getKeys());

		} catch (final Exception e) {
			LOGGER.error("Error while fetching file for {} merchant ", merchantStoreCode);
			throw new ServiceException(e);
		}

	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getRootName() {
		return rootName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addFolder(String merchantStoreCode, String folderName, Optional<String> path) throws ServiceException {
		
		
		String nodePath = this.getNodePath(merchantStoreCode, FileContentType.IMAGE);
		
		StringBuilder appender = new StringBuilder();
		appender.append(nodePath).append(Constants.SLASH);

		path.ifPresent(appender::append);
		
		
		//Put logic in a method
		
		Fqn folderFqn = Fqn.fromString(appender.toString());

		Node<String, Object> nd = cacheManager.getTreeCache().getRoot().getChild(folderFqn);

		if (nd == null) {

			cacheManager.getTreeCache().getRoot().addChild(folderFqn);
			nd = cacheManager.getTreeCache().getRoot().getChild(folderFqn);

		}
		
		appender.append(Constants.SLASH).append(folderName);
		
		Fqn newFolderFqn = Fqn.fromString(appender.toString());
		cacheManager.getTreeCache().getRoot().addChild(newFolderFqn);

	}

	@Override
	public void removeFolder(String merchantStoreCode, String folderName, Optional<String> path) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> listFolders(String merchantStoreCode, Optional<String> path) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CMSManager getCmsManager() {
    	return null;
  	}

}
