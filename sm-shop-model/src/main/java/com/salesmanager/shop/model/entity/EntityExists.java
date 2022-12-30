package com.salesmanager.shop.model.entity;

import java.io.Serializable;

public class EntityExists implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean exists = false;
	
	public EntityExists() {
		
	}

	public EntityExists(boolean exists) {
		this.exists = exists;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
