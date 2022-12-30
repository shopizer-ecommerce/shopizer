package com.salesmanager.core.model.catalog.product.variant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;


@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_VARIANT",
indexes = @Index(columnList = "PRODUCT_ID"),
uniqueConstraints = 
        @UniqueConstraint(columnNames = { 
        "PRODUCT_ID",
		"SKU" }))
public class ProductVariant extends SalesManagerEntity<Long, ProductVariant> implements Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRODUCT_VARIANT_ID", unique = true, nullable = false)
	@TableGenerator(name = "TABLE_GEN", 
	table = "SM_SEQUENCER", 
	pkColumnName = "SEQ_NAME", 
	valueColumnName = "SEQ_COUNT", 
	pkColumnValue = "PRODUCT_VAR_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;


	@Embedded
	private AuditSection auditSection = new AuditSection();

	@Column(name = "DATE_AVAILABLE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAvailable = new Date();
	
	@Column(name = "AVAILABLE")
	private boolean available = true;
	
	@Column(name = "DEFAULT_SELECTION")
	private boolean defaultSelection = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_VARIATION_ID", nullable = true)
	private ProductVariation variation;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	
	@Column(name = "CODE", nullable = true)
	private String code;
	
	@Column(name="SORT_ORDER")
	private Integer sortOrder = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_VARIATION_VALUE_ID", nullable = true)
	private ProductVariation variationValue;

	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name = "SKU")
	private String sku;
	
	@ManyToOne(targetEntity = ProductVariantGroup.class)
	@JoinColumn(name = "PRODUCT_VARIANT_GROUP_ID", nullable = true)
	private ProductVariantGroup productVariantGroup;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="productVariant")
	private Set<ProductAvailability> availabilities = new HashSet<ProductAvailability>();


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

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public ProductVariation getVariation() {
		return variation;
	}

	public void setVariation(ProductVariation variation) {
		this.variation = variation;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductVariation getVariationValue() {
		return variationValue;
	}

	public void setVariationValue(ProductVariation variationValue) {
		this.variationValue = variationValue;
	}

	public boolean isDefaultSelection() {
		return defaultSelection;
	}

	public void setDefaultSelection(boolean defaultSelection) {
		this.defaultSelection = defaultSelection;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	public String getCode() { return code; }
	 
	public void setCode(String code) { this.code = code; }

	public ProductVariantGroup getProductVariantGroup() {
		return productVariantGroup;
	}

	public void setProductVariantGroup(ProductVariantGroup productVariantGroup) {
		this.productVariantGroup = productVariantGroup;
	}

	public Set<ProductAvailability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(Set<ProductAvailability> availabilities) {
		this.availabilities = availabilities;
	}
	



}
