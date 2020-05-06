package com.salesmanager.core.model.catalog.product.price;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.utils.CloneUtils;

@Entity
@Table(name = "PRODUCT_PRICE", schema=SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductPrice extends SalesManagerEntity<Long, ProductPrice> {
	private static final long serialVersionUID = 1L;
	
	public final static String DEFAULT_PRICE_CODE="base";

	@Id
	@Column(name = "PRODUCT_PRICE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_PRICE_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productPrice", cascade = CascadeType.ALL)
	private Set<ProductPriceDescription> descriptions = new HashSet<ProductPriceDescription>();

	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name = "PRODUCT_PRICE_CODE", nullable=false)
	private String code = DEFAULT_PRICE_CODE;

	@Column(name = "PRODUCT_PRICE_AMOUNT", nullable=false)
	private BigDecimal productPriceAmount = new BigDecimal(0);

	@Column(name = "PRODUCT_PRICE_TYPE", length=20)
	@Enumerated(value = EnumType.STRING)
	private ProductPriceType productPriceType = ProductPriceType.ONE_TIME;

	@Column(name = "DEFAULT_PRICE")
	private boolean defaultPrice = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCT_PRICE_SPECIAL_ST_DATE")
	private Date productPriceSpecialStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCT_PRICE_SPECIAL_END_DATE")
	private Date productPriceSpecialEndDate;

	@Column(name = "PRODUCT_PRICE_SPECIAL_AMOUNT")
	private BigDecimal productPriceSpecialAmount;
	
	@JsonIgnore
	@ManyToOne(targetEntity = ProductAvailability.class)
	@JoinColumn(name = "PRODUCT_AVAIL_ID", nullable = false)
	private ProductAvailability productAvailability;
	

	public ProductPrice() {
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}




	public BigDecimal getProductPriceAmount() {
		return productPriceAmount;
	}

	public void setProductPriceAmount(BigDecimal productPriceAmount) {
		this.productPriceAmount = productPriceAmount;
	}


	
	public Date getProductPriceSpecialStartDate() {
		return CloneUtils.clone(productPriceSpecialStartDate);
	}

	public void setProductPriceSpecialStartDate(
			Date productPriceSpecialStartDate) {
		this.productPriceSpecialStartDate = CloneUtils.clone(productPriceSpecialStartDate);
	}

	public Date getProductPriceSpecialEndDate() {
		return CloneUtils.clone(productPriceSpecialEndDate);
	}

	public void setProductPriceSpecialEndDate(Date productPriceSpecialEndDate) {
		this.productPriceSpecialEndDate = CloneUtils.clone(productPriceSpecialEndDate);
	}



	public BigDecimal getProductPriceSpecialAmount() {
		return productPriceSpecialAmount;
	}

	public void setProductPriceSpecialAmount(
			BigDecimal productPriceSpecialAmount) {
		this.productPriceSpecialAmount = productPriceSpecialAmount;
	}



	public Set<ProductPriceDescription> getDescriptions() {
		return descriptions;
	}



	public void setDescriptions(Set<ProductPriceDescription> descriptions) {
		this.descriptions = descriptions;
	}



	public boolean isDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(boolean defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public void setProductAvailability(ProductAvailability productAvailability) {
		this.productAvailability = productAvailability;
	}

	public ProductAvailability getProductAvailability() {
		return productAvailability;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setProductPriceType(ProductPriceType productPriceType) {
		this.productPriceType = productPriceType;
	}

	public ProductPriceType getProductPriceType() {
		return productPriceType;
	}


}
