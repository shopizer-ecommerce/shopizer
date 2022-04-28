package com.salesmanager.core.model.catalog.product.instance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_INSTANCE_AVAILABILITY", 
	uniqueConstraints = @UniqueConstraint(columnNames = { "PRODUCT_INSTANCE_ID", "MERCHANT_ID" }))
public class ProductInstanceAvailability extends SalesManagerEntity<Long, ProductInstanceAvailability> implements Auditable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PRODUCT_INST_AVAIL_ID", unique=true, nullable=false)
	@TableGenerator(
		 name = "TABLE_GEN", 
		 table = "SM_SEQUENCER", 
		 pkColumnName = "SEQ_NAME", 
		 valueColumnName = "SEQ_COUNT", 
		 pkColumnValue = "PRD_INST_AVAIL_ID_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@ManyToOne(targetEntity = ProductInstance.class)
	@JoinColumn(name = "PRODUCT_INSTANCE_ID", nullable = false)
	private ProductInstance productInstance;

	@Column(name = "QUANTITY")
	private int quantity = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	private MerchantStore merchantStore;
	
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "PRODUCT_INSTANCE_ID", referencedColumnName = "PRODUCT_IDENTIFIER_ID")
	@OneToMany(targetEntity=ProductPrice.class,cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY, orphanRemoval = true)
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

	public ProductInstance getProductInstance() {
		return productInstance;
	}

	public void setProductInstance(ProductInstance productInstance) {
		this.productInstance = productInstance;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<ProductPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ProductPrice> prices) {
		this.prices = prices;
	}

}
