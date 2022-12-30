package com.salesmanager.core.model.catalog.marketplace;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * A marketplace is the main grouping for the state of product presentation.
 * MarketPlace belongs to a main MerchantStore
 * A MerchantStore can have a single MarketPlace
 * A MarketPlace allows to determine the main MerchantStore allowing determination of content
 * and configurations of a given MarketPlace. A MarketPlace has a list of Catalog created by each MerchantStore
 * Each Catalog contains a list of Product. A MarketPlace has also a list of Category that merchant cannot change.
 * Only the MarketPlace can decide which category are shown and which catalog is part of product offering
 * @author c.samson
 *
 */
public class MarketPlace extends SalesManagerEntity<Long, MarketPlace> implements Auditable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MerchantStore store;
	
	private Long id;
	
	private String code;
	
	private Set<Catalog> catalogs = new HashSet<Catalog>();
	
	@Embedded
	private AuditSection auditSection = new AuditSection();

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		this.auditSection = auditSection;	
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public MerchantStore getStore() {
		return store;
	}

	public void setStore(MerchantStore store) {
		this.store = store;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
