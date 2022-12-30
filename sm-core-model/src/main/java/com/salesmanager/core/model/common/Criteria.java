package com.salesmanager.core.model.common;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;

public class Criteria {

	// legacy pagination
	private int startIndex = 0;
	private int maxCount = 0;
	// new pagination
	private int startPage = 0;
	private int pageSize = 10;
	private boolean legacyPagination = true;
	private String code;
	private String name;
	private String language;
	private String user;
	private String storeCode;
	private List<Integer> storeIds;

	private CriteriaOrderBy orderBy = CriteriaOrderBy.DESC;
	private String criteriaOrderByField;
	private String search;

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCriteriaOrderByField() {
		return criteriaOrderByField;
	}

	public void setCriteriaOrderByField(String criteriaOrderByField) {
		this.criteriaOrderByField = criteriaOrderByField;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isLegacyPagination() {
		return legacyPagination;
	}

	public void setLegacyPagination(boolean legacyPagination) {
		this.legacyPagination = legacyPagination;
	}

	public List<Integer> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(List<Integer> storeIds) {
		this.storeIds = storeIds;
	}


}