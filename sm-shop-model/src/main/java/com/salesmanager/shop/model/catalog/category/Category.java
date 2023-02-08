package com.salesmanager.shop.model.catalog.category;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.Entity;


public class Category extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
<<<<<<< HEAD
=======
	private CategoryDescription description;
>>>>>>> a2316b73a7dd32791c9a9786e4f5dc6ae89a4743
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
<<<<<<< HEAD
=======
	public CategoryDescription getDescription() {
		return description;
	}
	public void setDescription(CategoryDescription description) {
		this.description = description;
	}
>>>>>>> a2316b73a7dd32791c9a9786e4f5dc6ae89a4743
	
}
