package com.salesmanager.shop.model.content;

public class ReadableContentBox extends ReadableContentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String boxContent;
	private String image;

	public String getBoxContent() {
		return boxContent;
	}

	public void setBoxContent(String boxContent) {
		this.boxContent = boxContent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
