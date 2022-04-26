package com.salesmanager.core.model.catalog.product.attribute;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Deprecated
/** use ProductVariation **/
@Entity
@Table(name="PRODUCT_VARIANT", uniqueConstraints=
	@UniqueConstraint(columnNames = {"PRODUCT_VARIANT_ID"}))
public class ProductVariant extends SalesManagerEntity<Long, ProductVariant> implements Auditable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Id
	@Column(name = "PRODUCT_VARIANT_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_VARI_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OPTION_ID", nullable=false)
	private ProductOption productOption;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OPTION_VALUE_ID", nullable=false)
	private ProductOptionValue productOptionValue;
	
	@Column(name="SORT_ORDER")
	private Integer sortOrder;	
	
	@Column(name="VARIANT_DEFAULT")
	private boolean variantDefault=false;
	
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
		return this.id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
		
	}



}
