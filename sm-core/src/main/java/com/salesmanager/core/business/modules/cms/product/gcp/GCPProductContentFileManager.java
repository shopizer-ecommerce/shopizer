package com.salesmanager.core.business.modules.cms.product.gcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.api.gax.paging.Page;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.Storage.BucketField;
import com.google.cloud.storage.Storage.BucketGetOption;
import com.google.cloud.storage.StorageOptions;
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
  
  private static String DEFAULT_BUCKET_NAME = "shopizer";
  
  private static final Logger LOGGER = LoggerFactory.getLogger(GCPProductContentFileManager.class);

  

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
    InputStream inputStream = null;
    try {
      Storage storage = StorageOptions.getDefaultInstance().getService();
      
      String bucketName = bucketName();
      
      if(!this.bucketExists(storage, bucketName)) {
        return null;
      }

      Blob blob = storage.get(BlobId.of(bucketName, filePath(merchantStoreCode,productCode, size.name(), imageName)));

      ReadChannel reader = blob.reader();
      
      inputStream = Channels.newInputStream(reader);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, outputStream);
      OutputContentFile ct = new OutputContentFile();
      ct.setFile(outputStream);
      ct.setFileName(blob.getName());

      

      return ct;
    } catch (final Exception e) {
      LOGGER.error("Error while getting files", e);
      throw new ServiceException(e);
  
    } finally {
      if(inputStream!=null) {
        try {
          inputStream.close();
        } catch(Exception ignore) {}
      }
      
    }
  
  }

  @Override
  public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException {

    return null;
    
  }

  @Override
  public List<OutputContentFile> getImages(Product product) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List files
   */
  @Override
  public List<OutputContentFile> getImages(String merchantStoreCode,
      FileContentType imageContentType) throws ServiceException {
    
    InputStream inputStream = null;
    try {
      Storage storage = StorageOptions.getDefaultInstance().getService();
      
      String bucketName = bucketName();
      
      if(!this.bucketExists(storage, bucketName)) {
        return null;
      }
      
      Page<Blob> blobs =
          storage.list(
              bucketName, BlobListOption.currentDirectory(), BlobListOption.prefix(merchantStoreCode));

      List<OutputContentFile> files = new ArrayList<OutputContentFile>();
      for (Blob blob : blobs.iterateAll()) {
        blob.getName();
        ReadChannel reader = blob.reader();
        inputStream = Channels.newInputStream(reader);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, outputStream);
        OutputContentFile ct = new OutputContentFile();
        ct.setFile(outputStream);
        files.add(ct);
      }

      return files;
    } catch (final Exception e) {
      LOGGER.error("Error while getting files", e);
      throw new ServiceException(e);
  
    } finally {
      if(inputStream!=null) {
        try {
          inputStream.close();
        } catch(Exception ignore) {}
      }
      
    }
  }

  @Override
  public void addProductImage(ProductImage productImage, ImageContentFile contentImage)
      throws ServiceException {
    
    Storage storage = StorageOptions.getDefaultInstance().getService();
    
    String bucketName = bucketName();

    if(!this.bucketExists(storage, bucketName)) {
      createBucket(storage, bucketName);
    }
    
    //build filename
    StringBuilder fileName = new StringBuilder()
        .append(filePath(productImage.getProduct().getMerchantStore().getCode(), productImage.getProduct().getSku(), contentImage.getFileContentType()))
        .append(productImage.getProductImage());
    
    
      try {
        byte[] targetArray = IOUtils.toByteArray(contentImage.getFile());
        BlobId blobId = BlobId.of(bucketName, fileName.toString());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        storage.create(blobInfo, targetArray);
        Acl acl = storage.createAcl(blobId, Acl.of(User.ofAllUsers(), Role.READER));
      } catch (IOException ioe) {
        throw new ServiceException(ioe);
      }

    
  }

  @Override
  public void removeProductImage(ProductImage productImage) throws ServiceException {
    
    //delete all image sizes
    Storage storage = StorageOptions.getDefaultInstance().getService();

    List<String> sizes = Arrays.asList(SMALL, LARGE);
    for(String size : sizes) {
      String filePath = filePath(productImage.getProduct().getMerchantStore().getCode(), productImage.getProduct().getSku(), size, productImage.getProductImage());
      BlobId blobId = BlobId.of(bucketName(), filePath);
      if(blobId==null) {
        LOGGER.info("Image path " + filePath + " does not exist");
        return;
        //throw new ServiceException("Image not found " + productImage.getProductImage());
      }
      boolean deleted = storage.delete(blobId);
      if (!deleted) {
        LOGGER.error("Cannot delete image [" + productImage.getProductImage() + "]");
      }
    }
  
  }

  @Override
  public void removeProductImages(Product product) throws ServiceException {

    
    Storage storage = StorageOptions.getDefaultInstance().getService();
    
    String bucketName = bucketName();

    Page<Blob> blobs =
        storage.list(
            bucketName, BlobListOption.currentDirectory(), BlobListOption.prefix(product.getSku()));

    
    for (Blob blob : blobs.iterateAll()) {
      // do something with the blob
      storage.delete(blob.getBlobId());
    }
    

  }

  @Override
  public void removeImages(String merchantStoreCode) throws ServiceException {
    Storage storage = StorageOptions.getDefaultInstance().getService();
    
    String bucketName = bucketName();

    Page<Blob> blobs =
        storage.list(
            bucketName, BlobListOption.currentDirectory(), BlobListOption.prefix(merchantStoreCode));

    
    for (Blob blob : blobs.iterateAll()) {
      // do something with the blob
      storage.delete(blob.getBlobId());
    }

  }
  
  private String bucketName() {
    String bucketName = gcpAssetsManager.getRootName();
    if (StringUtils.isBlank(bucketName)) {
      bucketName = DEFAULT_BUCKET_NAME;
    }
    return bucketName;
  }
  
  private boolean bucketExists(Storage storage, String bucketName) {
    Bucket bucket = storage.get(bucketName, BucketGetOption.fields(BucketField.NAME));
    if (bucket == null || !bucket.exists()) {
      return false;
    }
    return true;
  }
  
  private Bucket createBucket(Storage storage, String bucketName) {
    return storage.create(BucketInfo.of(bucketName));
  }
  
  private String filePath(String merchant, String sku, FileContentType contentImage) {
      StringBuilder sb = new StringBuilder();
      sb.append("products").append(Constants.SLASH);
      sb.append(merchant)
      .append(Constants.SLASH).append(sku).append(Constants.SLASH);

      // small large
      if (contentImage.name().equals(FileContentType.PRODUCT.name())) {
        sb.append(SMALL);
      } else if (contentImage.name().equals(FileContentType.PRODUCTLG.name())) {
        sb.append(LARGE);
      }

      return sb.append(Constants.SLASH).toString();
    
  }
  
  private String filePath(String merchant, String sku, String size, String fileName) {
    StringBuilder sb = new StringBuilder();
    sb.append("products").append(Constants.SLASH);
    sb.append(merchant)
    .append(Constants.SLASH).append(sku).append(Constants.SLASH);
    
    sb.append(size);
    sb.append(Constants.SLASH).append(fileName);

    return sb.toString();
  
  }


}