package com.salesmanager.core.model.catalog.product.attribute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;



import javax.validation.constraints.NotEmpty;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;


@Entity
@Table(name="PRODUCT_OPTION", schema=SchemaConstant.SALESMANAGER_SCHEMA, indexes = { @Index(name="PRD_OPTION_CODE_IDX", columnList = "PRODUCT_OPTION_CODE")}, uniqueConstraints=
	@UniqueConstraint(columnNames = {"MERCHANT_ID", "PRODUCT_OPTION_CODE"}))
public class ProductOption extends SalesManagerEntity<Long, ProductOption> {
	private static final long serialVersionUID = -2019269055342226086L;
	
	@Id
	@Column(name="PRODUCT_OPTION_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_OPTION_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Column(name="PRODUCT_OPTION_SORT_ORD")
	private Integer productOptionSortOrder;
	
	@Column(name="PRODUCT_OPTION_TYPE", length=10)
	private String productOptionType;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOption")
	private Set<ProductOptionDescription> descriptions = new HashSet<ProductOptionDescription>();
	
	@Transient
	private List<ProductOptionDescription> descriptionsList = new ArrayList<ProductOptionDescription>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	@Column(name="PRODUCT_OPTION_READ")
	private boolean readOnly;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name="PRODUCT_OPTION_CODE")
	//@Index(name="PRD_OPTION_CODE_IDX")
	private String code;
	
	public ProductOption() {
	}
	
	public Integer getProductOptionSortOrder() {
		return productOptionSortOrder;
	}
	
	public void setProductOptionSortOrder(Integer productOptionSortOrder) {
		this.productOptionSortOrder = productOptionSortOrder;
	}
	
	public String getProductOptionType() {
		return productOptionType;
	}

	public void setProductOptionType(String productOptionType) {
		this.productOptionType = productOptionType;
	}
	
	public Set<ProductOptionDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<ProductOptionDescription> descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}



	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public void setDescriptionsList(List<ProductOptionDescription> descriptionsList) {
		this.descriptionsList = descriptionsList;
	}

	public List<ProductOptionDescription> getDescriptionsList() {
		return descriptionsList;
	}
	

	public List<ProductOptionDescription> getDescriptionsSettoList() {
		if(descriptionsList==null || descriptionsList.size()==0) {
			descriptionsList = new ArrayList<ProductOptionDescription>(this.getDescriptions());
		} 
		return descriptionsList;

	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isReadOnly() {
		return readOnly;
	}
}
