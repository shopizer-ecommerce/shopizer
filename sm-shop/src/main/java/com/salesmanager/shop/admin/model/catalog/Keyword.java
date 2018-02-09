package com.salesmanager.shop.admin.model.catalog;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Post keyword from the admin
 * @author Carl Samson
 *
 */
public class Keyword implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long productId;
	private String languageCode;
	@NotEmpty
	private String keyword;
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getProductId() {
		return productId;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

}
