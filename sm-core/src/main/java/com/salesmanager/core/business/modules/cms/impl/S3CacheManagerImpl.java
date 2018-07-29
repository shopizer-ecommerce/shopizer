package com.salesmanager.core.business.modules.cms.impl;

public class S3CacheManagerImpl implements CMSManager {
	
	
	private String bucketName;
	
	public S3CacheManagerImpl(String bucketName){
		this.bucketName = bucketName;
	}

	
	//https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-transfermanager.html
	@Override
	public String getRootName() {
		return bucketName;
	}

	@Override
	public String getLocation() {
		return bucketName;
	}


	public String getBucketName() {
		return bucketName;
	}


}
