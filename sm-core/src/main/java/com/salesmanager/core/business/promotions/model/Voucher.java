/**
 * 
 */
package com.salesmanager.core.business.promotions.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.common.model.audit.Auditable;
import com.salesmanager.core.business.generic.model.SalesManagerEntity;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.constants.SchemaConstant;

/**
 * <p>Model class representing Vouchers in Shopizer, this model class
 * is responsible for defining common attributes available to each 
 * Voucher type implementation </p>
 * <p>
 * Voucher implementation follow  single table per class hierarchy,
 * instances are distinguished by a special discriminator column.
 * </p>
 * @author Umesh Awasthi
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VOUVHERTYPE",discriminatorType=DiscriminatorType.STRING)
@Table(name="VOUCHERS", schema = SchemaConstant.SALESMANAGER_SCHEMA)
@DiscriminatorValue("Vouchers")
public class Voucher extends SalesManagerEntity<Long,Voucher> implements Auditable  {

	private static final long serialVersionUID = -9029982900169883974L;

	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Id
	@Column (name ="VOUCHER_ID" , unique=true , nullable=false )
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
		pkColumnValue = "ORDER_ID_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="FREESHIPPING")
	private boolean freeShipping;
	
	@Column(name="VALUE")
	private Double voucherValue;
	
	@Column(name="ISABSOLUTE")
	private boolean absoluteValue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vouchers")
	private Set<Order> orders = new HashSet<Order>();
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
	   this.id=id;
		
	}
	
	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}
	
	@Override
	public void setAuditSection(AuditSection auditSection) {
		this.auditSection = auditSection;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	public Double getVoucherValue() {
		return voucherValue;
	}

	public void setVoucherValue(Double voucherValue) {
		this.voucherValue = voucherValue;
	}

	public boolean isAbsoluteValue() {
		return absoluteValue;
	}

	public void setAbsoluteValue(boolean absoluteValue) {
		this.absoluteValue = absoluteValue;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	

}
