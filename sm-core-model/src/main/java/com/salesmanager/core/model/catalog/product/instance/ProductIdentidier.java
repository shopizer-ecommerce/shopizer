package com.salesmanager.core.model.catalog.product.instance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;

public class ProductIdentidier extends SalesManagerEntity<Long, ProductIdentidier> implements Auditable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PRODUCT_IDENTIFIER_ID", unique=true, nullable=false)
	@TableGenerator(
		 name = "TABLE_GEN", 
		 table = "SM_SEQUENCER", 
		 pkColumnName = "SEQ_NAME", 
		 valueColumnName = "SEQ_COUNT", 
		 pkColumnValue = "PRODUCT_SEQ_ID_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name = "SKU")
	private String sku;
	
	@Column(name = "QUANTITY")
	private int quantity = 0;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_INSTANCE_ID", referencedColumnName = "PRODUCT_IDENTIFIER_ID")
	private List<ProductPrice> prices = new ArrayList<ProductPrice>();

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		this.auditSection = audit;
		
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

}
