package com.salesmanager.web.admin.entity.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class StoreLanding {
	
	@Valid
	private List<StoreLandingDescription> descriptions = new ArrayList<StoreLandingDescription>();

	public void setDescriptions(List<StoreLandingDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public List<StoreLandingDescription> getDescriptions() {
		return descriptions;
	}

}
