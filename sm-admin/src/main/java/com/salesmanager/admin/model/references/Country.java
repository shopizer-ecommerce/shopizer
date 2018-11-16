package com.salesmanager.admin.model.references;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.admin.model.AbstractModel;

public class Country extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	
	private List<Zone> zones = new ArrayList<Zone>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Zone> getZones() {
		return zones;
	}
	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

}
