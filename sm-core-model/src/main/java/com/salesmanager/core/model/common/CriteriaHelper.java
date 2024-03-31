package com.salesmanager.core.model.common;

import java.util.List;

public class CriteriaHelper{

    private String search;
    private String storeCode;
    private String code;

    private String user;
    private String name;

    private List<Integer> storeIds;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


    public String getCode() {
		return code;
	}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Integer> storeIds) {
        this.storeIds = storeIds;
    }

    public String getName() {
		return name;
	}

}
