package com.salesmanager.shop.model.catalog.product;

import org.springframework.web.multipart.MultipartFile;
import com.salesmanager.shop.model.entity.Entity;

public class PersistableImage extends Entity {
	

	   private static final long serialVersionUID = 1L;
	   private boolean defaultImage;
	   private int imageType = 0;
	   private String name = null;
	   private String path = null;

	   private MultipartFile[] files;
	   private byte[] bytes = null;
	   private String contentType = null;
	   
	   
	   /**
	    * An external image url
	    */
	   private String imageUrl = null;
	   


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


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public int getImageType() {
		return imageType;
	}


	public void setImageType(int imageType) {
		this.imageType = imageType;
	}


	public boolean isDefaultImage() {
		return defaultImage;
	}


	public void setDefaultImage(boolean defaultImage) {
		this.defaultImage = defaultImage;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


  public MultipartFile[] getFiles() {
    return files;
  }


  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }

}
