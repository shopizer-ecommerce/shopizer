package com.salesmanager.shop.model.entity;

import java.io.Serializable;

public class Entity implements Serializable {
  
    public Entity() {}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = 0L;
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

}
