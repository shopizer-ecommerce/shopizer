package com.salesmanager.core.business.modules.cms.content.gcp;

import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob.BlobSourceOption;
import com.google.cloud.storage.Storage.BlobField;
import com.google.cloud.storage.Storage.BucketGetOption;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.ContentAssetsManager;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
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

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LoggerFactory.getLogger(GCPStaticContentAssetsManagerImpl.class);

  @Autowired
  @Qualifier("gcpAssetsManager")
  private CMSManager cmsManager;

  @Override
	public OutputContentFile getFile(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType, String contentName)
			throws ServiceException {
    try {
      String bucketName = bucketName();
      // Instantiates a client
      Storage storage = StorageOptions.getDefaultInstance().getService();

      Blob blob = storage.get(BlobId.of(bucketName, nodePath(merchantStoreCode, fileContentType) + contentName));
      LOGGER.info("Content getFile");

      return getOutputContentFile(blob.getContent(BlobSourceOption.generationMatch()));

    } catch (Exception e) {
      LOGGER.error("Error while getting file", e);
      throw new ServiceException(e);
    }
  }

	@Override
	public List<String> getFileNames(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType)
			throws ServiceException {
		try {
			String bucketName = bucketName();
			Storage storage = StorageOptions.getDefaultInstance().getService();
			long bucketMetaGeneration = 42;
			Bucket bucket = storage.get(bucketName, BucketGetOption.metagenerationMatch(bucketMetaGeneration));
			Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(nodePath(merchantStoreCode, fileContentType)),
				Storage.BlobListOption.fields(BlobField.NAME));
	
			List<String> fileNames = new ArrayList<String>();
	
			for (Blob blob : blobs.iterateAll()) {
				if (isInsideSubFolder(blob.getName()))
				continue;
				String mimetype = URLConnection.guessContentTypeFromName(blob.getName());
				if (!StringUtils.isBlank(mimetype)) {
				fileNames.add(getName(blob.getName()));
				}
	
			}
	
			LOGGER.info("Content get file names");
			return fileNames;
		} catch (Exception e) {
			LOGGER.error("Error while getting file names", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OutputContentFile> getFiles(String merchantStoreCode, Optional<String> folderPath, FileContentType fileContentType)
			throws ServiceException {
		try {

			List<String> fileNames = getFileNames(merchantStoreCode, folderPath, fileContentType);
			List<OutputContentFile> files = new ArrayList<OutputContentFile>();
		
			for (String fileName : fileNames) {
				files.add(getFile(merchantStoreCode, folderPath, fileContentType, fileName));
			}
		
			LOGGER.info("Content get file names");
			return files;
		} catch (Exception e) {
		LOGGER.error("Error while getting file names", e);
		throw new ServiceException(e);
		}
	}

	@Override
	public void addFile(String merchantStoreCode, Optional<String> folderPath, InputContentFile inputStaticContentData) throws ServiceException {

		try {
			LOGGER.debug("Adding file " + inputStaticContentData.getFileName());
			String bucketName = bucketName();
	  
			String nodePath = nodePath(merchantStoreCode, inputStaticContentData.getFileContentType());
	  
			Storage storage = StorageOptions.getDefaultInstance().getService();
	  
			BlobId blobId = BlobId.of(bucketName, nodePath + inputStaticContentData.getFileName());
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(inputStaticContentData.getFileContentType().name())
				.build();
			byte[] targetArray = new byte[inputStaticContentData.getFile().available()];
			inputStaticContentData.getFile().read(targetArray);
			storage.create(blobInfo, targetArray);
			LOGGER.info("Content add file");
		} catch (IOException e) {
			LOGGER.error("Error while adding file", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void addFiles(String merchantStoreCode, Optional<String> folderPath, List<InputContentFile> inputStaticContentDataList)
			throws ServiceException {
		if (CollectionUtils.isNotEmpty(inputStaticContentDataList)) {
			for (InputContentFile inputFile : inputStaticContentDataList) {
				this.addFile(merchantStoreCode, folderPath, inputFile);
			}
		}
	}

	@Override
	public void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName, Optional<String> folderPath)
			throws ServiceException {
		try {
			String bucketName = bucketName();
			Storage storage = StorageOptions.getDefaultInstance().getService();
			storage.delete(bucketName, nodePath(merchantStoreCode, staticContentType) + fileName);
		
			LOGGER.info("Remove file");
		} catch (final Exception e) {
			LOGGER.error("Error while removing file", e);
			throw new ServiceException(e);
		}			  
	}

	@Override
	public void removeFiles(String merchantStoreCode, Optional<String> folderPath) throws ServiceException {
		try {
			// get buckets
			String bucketName = bucketName();
	
			Storage storage = StorageOptions.getDefaultInstance().getService();
			storage.delete(bucketName, nodePath(merchantStoreCode));
	
			LOGGER.info("Remove folder");
		} catch (final Exception e) {
			LOGGER.error("Error while removing folder", e);
			throw new ServiceException(e);	
		}
	}

  
	public CMSManager getCmsManager() {
		return cmsManager;
	}

	public void setCmsManager(CMSManager cmsManager) {
		this.cmsManager = cmsManager;
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
