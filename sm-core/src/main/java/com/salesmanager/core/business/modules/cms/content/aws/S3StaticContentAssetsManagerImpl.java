package com.salesmanager.core.business.modules.cms.content.aws;

import java.io.ByteArrayOutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.ContentAssetsManager;
import com.salesmanager.core.business.modules.cms.content.infinispan.CmsStaticContentFileManagerImpl;
import com.salesmanager.core.business.modules.cms.impl.CacheManager;
import com.salesmanager.core.business.modules.cms.impl.CacheManagerImpl;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

public class S3StaticContentAssetsManagerImpl implements ContentAssetsManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger( S3StaticContentAssetsManagerImpl.class );
    
	
	private static String DEFAULT_BUCKET_NAME = "shopizer";
	private static final String ROOT_NAME="static-merchant-";
	private static S3StaticContentAssetsManagerImpl fileManager = null;
    
    private String rootName = ROOT_NAME;
    
    private CacheManager cacheManager;
    
    public static S3StaticContentAssetsManagerImpl getInstance()
    {

        if ( fileManager == null )
        {
            fileManager = new S3StaticContentAssetsManagerImpl();
        }

        return fileManager;

    }

	@Override
	public OutputContentFile getFile(String merchantStoreCode, FileContentType fileContentType, String contentName)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFileNames(String merchantStoreCode, FileContentType fileContentType)
			throws ServiceException {
        try
        {
        	//get buckets
        	String bucketName = bucketName();
        	
        	String nodePath = merchantStoreCode;
        	
        	ListObjectsV2Request listObjectsRequest = 
                      new ListObjectsV2Request()
                            .withBucketName(bucketName)
                            .withPrefix(nodePath + "/");
        	
        	List<String> fileNames = null;
        	
            final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
            ListObjectsV2Result results = s3.listObjectsV2(listObjectsRequest);
            List<S3ObjectSummary> objects = results.getObjectSummaries();
            for (S3ObjectSummary os: objects) {
                if(fileNames == null) {
                	fileNames = new ArrayList<String>();
                }
    	        String mimetype = URLConnection.guessContentTypeFromName(os.getKey());
    	        if(!StringUtils.isBlank(mimetype)) {
        	        fileNames.add(os.getKey());
    	        }
            }


            LOGGER.info( "Content get file names" );
            return fileNames;
        }
        catch ( final Exception e )
        {
            LOGGER.error( "Error while getting file names", e );
            throw new ServiceException( e );

        }
	}

	@Override
	public List<OutputContentFile> getFiles(String merchantStoreCode, FileContentType fileContentType)
			throws ServiceException {
        try
        {
        	//get buckets
        	String bucketName = bucketName();
        	
        	String nodePath = merchantStoreCode;
        	
        	ListObjectsV2Request listObjectsRequest = 
                      new ListObjectsV2Request()
                            .withBucketName(bucketName)
                            .withPrefix(nodePath + "/");

        	List<OutputContentFile> files = null;
            final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
            ListObjectsV2Result results = s3.listObjectsV2(listObjectsRequest);
            List<S3ObjectSummary> objects = results.getObjectSummaries();
            for (S3ObjectSummary os: objects) {
            	if(files == null) {
            		files = new ArrayList<OutputContentFile>();
            	}
    	        String mimetype = URLConnection.guessContentTypeFromName(os.getKey());
    	        if(!StringUtils.isBlank(mimetype)) {
    	            S3Object o = s3.getObject(bucketName, os.getKey());
    	            byte[] byteArray = IOUtils.toByteArray(o.getObjectContent());
    	            ByteArrayOutputStream baos = new ByteArrayOutputStream(byteArray.length);
    	            baos.write(byteArray, 0, byteArray.length);
    	            OutputContentFile ct = new OutputContentFile();
    	            ct.setFile(baos);
    	            files.add(ct);
    	        }
            }


            LOGGER.info( "Content getFiles" );
            return files;
        }
        catch ( final Exception e )
        {
            LOGGER.error( "Error while getting files", e );
            throw new ServiceException( e );

        }
	}

	@Override
	public void addFile(String merchantStoreCode, InputContentFile inputStaticContentData) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFiles(String merchantStoreCode, List<InputContentFile> inputStaticContentDataList)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFiles(String merchantStoreCode) throws ServiceException {
		// TODO Auto-generated method stub

	}
	
	private Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        
        if(named_bucket == null) {
        	
        }
        
        return named_bucket;
	}
	
    private Bucket createBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            System.out.format("Bucket %s already exists.\n", bucket_name);
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }
    
    private String bucketName() {
    	String bucketName = cacheManager.getLocation();
    	if(StringUtils.isBlank(bucketName)) {
    		bucketName = DEFAULT_BUCKET_NAME;
    	}
    	return bucketName;
    }

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

}
