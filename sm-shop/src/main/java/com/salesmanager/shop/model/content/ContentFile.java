package com.salesmanager.shop.model.content;


/**
 * Model object used in webservice
 * when creatin files
 * @author carlsamson
 *
 */
public class ContentFile {
	
	private String name;
	private byte[] file;
	private String contentType;
	
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}

}
