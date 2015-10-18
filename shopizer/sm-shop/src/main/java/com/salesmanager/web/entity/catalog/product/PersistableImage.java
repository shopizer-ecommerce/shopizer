package com.salesmanager.web.entity.catalog.product;

public class PersistableImage {
	
	   private byte[] bytes = null;


	   private String contentType = null;
	   
	   private String imageName;


	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}


	public byte[] getBytes() {
		return bytes;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getContentType() {
		return contentType;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getImageName() {
		return imageName;
	}

}
