package com.salesmanager.shop.model.entity;

import javax.validation.constraints.NotEmpty;

/**
 * Used as an input request object where an entity name and or id is important
 * @author carlsamson
 *
 */
public class NameEntity extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
