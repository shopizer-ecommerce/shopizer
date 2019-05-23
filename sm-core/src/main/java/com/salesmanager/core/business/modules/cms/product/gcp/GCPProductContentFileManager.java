package com.salesmanager.core.business.modules.cms.product.gcp;

import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.StorageOptions;
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
  
  private static String DEFAULT_BUCKET_NAME = "shopizer-content";

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
    try {
      //BlobId blobId = BlobId.of(bucketName(), "blobName");
      //Blob blob = storage.get(blobId);
      
      Storage storage = StorageOptions.getDefaultInstance().getService();
      
      String pattern = ".*abc.*";
      Pattern matchPattern = Pattern.compile(pattern);
      
      Page<Blob> blobs =
          storage.list(
              bucketName(), BlobListOption.currentDirectory(), BlobListOption.prefix("directory"));
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
      
    }
    return null;
  }

  @Override
  public void addProductImage(ProductImage productImage, ImageContentFile contentImage)
      throws ServiceException {
    // TODO Auto-generated method stub

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
  
  private String bucketName() {
    String bucketName = gcpAssetsManager.getRootName();
    if (StringUtils.isBlank(bucketName)) {
      bucketName = DEFAULT_BUCKET_NAME;
    }
    return bucketName;
  }

}
