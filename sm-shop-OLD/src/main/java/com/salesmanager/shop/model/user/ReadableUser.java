package com.salesmanager.shop.model.user;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.security.ReadablePermission;

public class ReadableUser extends UserEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReadableGroup> groups = new ArrayList<ReadableGroup>();
	private List<ReadablePermission> permissions = new ArrayList<ReadablePermission>();

	public List<ReadableGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ReadableGroup> groups) {
		this.groups = groups;
	}

	public List<ReadablePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<ReadablePermission> permissions) {
		this.permissions = permissions;
	}

}
