package com.salesmanager.core.model.common;

public class Criteria {
	
	
	private int startIndex = 0;
	private int maxCount = 0;
	private String code;
	private String language;
	
	private CriteriaOrderBy orderBy = CriteriaOrderBy.DESC;
	
	
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setOrderBy(CriteriaOrderBy orderBy) {
		this.orderBy = orderBy;
	}
	public CriteriaOrderBy getOrderBy() {
		return orderBy;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}



}