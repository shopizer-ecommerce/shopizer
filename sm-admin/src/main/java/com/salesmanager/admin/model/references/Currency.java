package com.salesmanager.admin.model.references;

import com.salesmanager.admin.model.common.AbstractModel;

public class Currency extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int supported;

	public int getSupported() {
		return supported;
	}
	public void setSupported(int supported) {
		this.supported = supported;
	}

}
