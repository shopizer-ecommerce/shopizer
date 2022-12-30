package com.salesmanager.core.business.modules.cms.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Interaction with GCP (Google Cloud Platform) with AWS S3
 * https://cloud.google.com/storage/docs/uploading-objects#storage-upload-object-java
 * 
 * @author carlsamson
 *
 */
@Component("gcpAssetsManager")
public class GCPCacheManagerImpl implements CMSManager {

  @Value("${config.cms.gcp.bucket}")
  private String bucketName;



  public GCPCacheManagerImpl() {}


  @Override
  public String getRootName() {
    return bucketName;
  }

  public String getBucketName() {
    return bucketName;
  }


  @Override
  public String getLocation() {
    // TODO Auto-generated method stub
    return null;
  }
  
  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }




}
