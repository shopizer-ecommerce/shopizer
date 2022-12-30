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
	private String lastAccess;
	private String loginTime;
	private String merchant;
	private String userName;
	private boolean active;

	private List<ReadablePermission> permissions = new ArrayList<ReadablePermission>();
	private List<ReadableGroup> groups = new ArrayList<ReadableGroup>();

	public List<ReadableGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ReadableGroup> groups) {
		this.groups = groups;
	}

	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public List<ReadablePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<ReadablePermission> permissions) {
		this.permissions = permissions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
