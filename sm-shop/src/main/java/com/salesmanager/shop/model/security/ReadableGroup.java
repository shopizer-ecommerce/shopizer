package com.salesmanager.shop.model.security;

/**
 * Object used for reading a group
 * @author carlsamson
 *
 */
public class ReadableGroup extends GroupEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
