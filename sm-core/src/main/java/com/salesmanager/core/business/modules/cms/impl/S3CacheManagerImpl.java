package com.salesmanager.core.business.modules.cms.impl;

/**
 * Interaction with AWS S3
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-transfermanager.html
 * 
 * @author carlsamson
 *
 */
public class S3CacheManagerImpl implements CMSManager {


  private String bucketName;
  private String regionName;

  public S3CacheManagerImpl(String bucketName, String regionName) {
    this.bucketName = bucketName;
    this.regionName = regionName;
  }


  @Override
  public String getRootName() {
    return bucketName;
  }

  @Override
  public String getLocation() {
    return regionName;
  }


  public String getBucketName() {
    return bucketName;
  }

  public String getRegionName() {
    return regionName;
  }


}
