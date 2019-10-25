package com.salesmanager.shop.model.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Folder containing content
 * images and other files
 * @author carlsamson
 *
 */
public class ContentFolder {
	
	private String path;
	List<Content> content = new ArrayList<Content>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}

}
