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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name="PRODUCT_VARIANT", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints=
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
	@JoinColumn(name="PRODUCT_ATTRIBUTE_ID", nullable=false)
	private ProductAttribute attribute;
	
	@Column(name="PRODUCT_AQUANTITY")
	private Integer productQuantity = 0;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCT_AVAIL_ID", nullable=false)
	private ProductAvailability productAvailability;
	
	
	@Override
	public AuditSection getAuditSection() {
		// TODO Auto-generated method stub
		return auditSection;
	}
	@Override
	public void setAuditSection(AuditSection audit) {
		// TODO Auto-generated method stub
		this.auditSection = audit;
		
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
		
	}
	public ProductAttribute getAttribute() {
		return attribute;
	}
	public void setAttribute(ProductAttribute attribute) {
		this.attribute = attribute;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public ProductAvailability getProductAvailability() {
		return productAvailability;
	}
	public void setProductAvailability(ProductAvailability productAvailability) {
		this.productAvailability = productAvailability;
	}

}
