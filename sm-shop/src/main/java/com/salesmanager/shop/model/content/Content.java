package com.salesmanager.shop.model.content;

import java.io.Serializable;

public abstract class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private String name;
	
	public Content(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
