package com.salesmanager.core.business.modules.cms.product.gcp;

import java.io.IOException;
import java.nio.channels.Channels;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import com.google.api.client.util.ByteStreams;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.business.modules.cms.product.ProductAssetsManager;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

@Component("gcpProductAssetsManager")
public class GCPProductContentFileManager implements ProductAssetsManager {
  
  @Autowired 
  private CMSManager gcpAssetsManager;
  
  private static String DEFAULT_BUCKET_NAME = "shopizer-products-";
  

  private final static String SMALL = "SMALL";
  private final static String LARGE = "LARGE";

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public OutputContentFile getProductImage(String merchantStoreCode, String productCode,
      String imageName) throws ServiceException {
    // TODO Auto-generated method stub
    
 
    
    return null;
  }

  @Override
  public OutputContentFile getProductImage(String merchantStoreCode, String productCode,
      String imageName, ProductImageSize size) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OutputContentFile> getImages(Product product) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OutputContentFile> getImages(String merchantStoreCode,
      FileContentType imageContentType) throws ServiceException {
    //try {
      //BlobId blobId = BlobId.of(bucketName(), "blobName");
      //Blob blob = storage.get(blobId);
      
/*      Storage storage = StorageOptions.getDefaultInstance().getService();
      Blob blob = storage.get(BlobId.of(new StringBuilder().append(DEFAULT_BUCKET_NAME).append(merchantStoreCode), srcFilename));
      
      String pattern = merchantStoreCode;
      Pattern matchPattern = Pattern.compile(pattern);
      
      Page<Blob> blobs =
          storage.list(
              bucketName(), BlobListOption.prefix(prefix)
              .currentDirectory(), BlobListOption.prefix("directory"));
      Page<Blob> blobs =
          storage.list(
              bucketName(), BlobListOption.currentDirectory(), new StringBuilder().append(DEFAULT_BUCKET_NAME).append(merchantStoreCode).toString());
      for (Blob blob : blobs.iterateAll()) {
        if (!blob.isDirectory() && matchPattern.matcher(blob.getName()).matches()) {
          return null;
          //results.add(blob.getName());
        }
      }
      //for (Blob blob : blobs.iterateAll()) {
        // do something with the blob
      //}
      // [END listBlobsWithDirectoryAndPrefix]
      //return blobs;
      
    } catch(Exception e) {
      
    }*/
    return null;
  }

  @Override
  public void addProductImage(ProductImage productImage, ImageContentFile contentImage)
      throws ServiceException {
    
    Storage storage = StorageOptions.getDefaultInstance().getService();
    
    String bucketName = this.bucketName(productImage.getProduct().getMerchantStore().getCode());

    Bucket bucket = null;
    if(!this.bucketExists(storage, bucketName)) {
      bucket = createBucket(storage, bucketName);
    }
    
    //build filename
    StringBuilder fileName = new StringBuilder()
        .append(filePath(productImage.getProduct().getMerchantStore().getCode(), productImage.getProduct().getSku(), contentImage))
        .append(productImage.getProductImage());
    
    
      try {
        byte[] targetArray = IOUtils.toByteArray(contentImage.getFile());
        BlobId blobId = BlobId.of(bucketName, fileName.toString());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentImage.getMimeType()).build();
        Blob blob = storage.create(blobInfo, targetArray);
      } catch (IOException ioe) {
        throw new ServiceException(ioe);
      }

    
  }

  @Override
  public void removeProductImage(ProductImage productImage) throws ServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeProductImages(Product product) throws ServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeImages(String merchantStoreCode) throws ServiceException {
    // TODO Auto-generated method stub

  }
  
  private String bucketName(String merchant) {
    String bucketName = gcpAssetsManager.getRootName();
    if (StringUtils.isBlank(bucketName)) {
      bucketName = DEFAULT_BUCKET_NAME;
    }
    return bucketName;
  }
  
  private boolean bucketExists(Storage storage, String bucketName) {
    Bucket bucket = storage.get(bucketName);
    if (bucket == null || !bucket.exists()) {
      return false;
    }
    return true;
  }
  
  private Bucket createBucket(Storage storage, String bucketName) {
    return storage.create(BucketInfo.of(bucketName));
  }
  
  private String filePath(String merchant, String sku, ImageContentFile contentImage) {
      StringBuilder sb = new StringBuilder();
      sb.append("products").append(Constants.SLASH);
      sb.append(merchant)
      .append(Constants.SLASH).append(sku).append(Constants.SLASH);

      // small large
      if (contentImage.getFileContentType().name().equals(FileContentType.PRODUCT.name())) {
        sb.append(SMALL);
      } else if (contentImage.getFileContentType().name().equals(FileContentType.PRODUCTLG.name())) {
        sb.append(LARGE);
      }

      return sb.append(Constants.SLASH).toString();
    
  }

}
