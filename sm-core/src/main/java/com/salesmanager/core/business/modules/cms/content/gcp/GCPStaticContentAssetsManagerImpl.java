package com.salesmanager.core.business.modules.cms.content.gcp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.ContentAssetsManager;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

/**
 * Content management for a given retailer using GC{ (Google Cloud Platform)
 * Cloud Storage with buckets
 * 
 * 
 * Linux/ Mac export GOOGLE_APPLICATION_CREDENTIALS="/path/to/file" For Windows
 * set GOOGLE_APPLICATION_CREDENTIALS="C:\path\to\file"
 * 
 * following this article: https://www.baeldung.com/java-google-cloud-storage
 * 
 * gsutil ls -L -b gs://shopizer-content
 * 
 * 
 * @author carlsamson
 *
 */
@Component("gcpContentAssetsManager")
public class GCPStaticContentAssetsManagerImpl implements ContentAssetsManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(GCPStaticContentAssetsManagerImpl.class);

	@Override
	public OutputContentFile getFile(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType, String contentName)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFileNames(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutputContentFile> getFiles(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(String merchantStoreCode, Optional<String> folderPath, InputContentFile inputStaticContentData) throws ServiceException {

		LOGGER.debug("Adding file " + inputStaticContentData.getFileName());
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BlobId blobId = BlobId.of("bucket", "blob_name");
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
		Blob blob = storage.create(blobInfo, /** image **/
				null);

	}

	@Override
	public void addFiles(String merchantStoreCode, Optional<String> folderPath, List<InputContentFile> inputStaticContentDataList)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName, Optional<String> folderPath)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFiles(String merchantStoreCode, Optional<String> folderPath) throws ServiceException {
		// TODO Auto-generated method stub

	}

	private Bucket getBucket() {

		// Bucket bucket = storage.create(BucketInfo.of("shopizer-content"));
		// //get bucket
		return null;

	}

	@Override
	public void addFolder(String merchantStoreCode, String folderName, Optional<String> folderPath) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFolder(String merchantStoreCode, String folderName, Optional<String> folderPath) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> listFolders(String merchantStoreCode, Optional<String> folderPath) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
