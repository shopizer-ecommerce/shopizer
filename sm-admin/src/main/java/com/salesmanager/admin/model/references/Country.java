package com.salesmanager.admin.model.references;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.admin.model.common.AbstractModel;

public class Country extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	private List<Zone> zones = new ArrayList<Zone>();

	public List<Zone> getZones() {
		return zones;
	}
	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

}
