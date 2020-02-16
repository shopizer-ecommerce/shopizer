package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

public class ReadableProductVariantValue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long option;//option id
	private Long value;//option value id
	


	public Long getValue() {
		return value;
	}



	public void setValue(Long value) {
		this.value = value;
	}



  public Long getOption() {
    return option;
  }



  public void setOption(Long option) {
    this.option = option;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



}
