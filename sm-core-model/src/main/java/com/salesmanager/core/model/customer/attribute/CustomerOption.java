package com.salesmanager.core.model.customer.attribute;

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
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;


@Entity
@Table(name="CUSTOMER_OPTION", indexes = { @Index(name="CUST_OPT_CODE_IDX", columnList = "CUSTOMER_OPT_CODE")}, uniqueConstraints=
	@UniqueConstraint(columnNames = {"MERCHANT_ID", "CUSTOMER_OPT_CODE"}))
public class CustomerOption extends SalesManagerEntity<Long, CustomerOption> {
	private static final long serialVersionUID = -2019269055342226086L;
	
	@Id
	@Column(name="CUSTOMER_OPTION_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUSTOMER_OPTION_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Column(name="SORT_ORDER")
	private Integer sortOrder = 0;
	
	@Column(name="CUSTOMER_OPTION_TYPE", length=10)
	private String customerOptionType;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z0-9_]*$")
	@Column(name="CUSTOMER_OPT_CODE")
	//@Index(name="CUST_OPT_CODE_IDX")
	private String code;
	
	@Column(name="CUSTOMER_OPT_ACTIVE")
	private boolean active;
	
	@Column(name="CUSTOMER_OPT_PUBLIC")
	private boolean publicOption;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOption")
	private Set<CustomerOptionDescription> descriptions = new HashSet<CustomerOptionDescription>();
	
	@Transient
	private List<CustomerOptionDescription> descriptionsList = new ArrayList<CustomerOptionDescription>();

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	public CustomerOption() {
	}
	

	
	public Set<CustomerOptionDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<CustomerOptionDescription> descriptions) {
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

	public void setDescriptionsList(List<CustomerOptionDescription> descriptionsList) {
		this.descriptionsList = descriptionsList;
	}

	public List<CustomerOptionDescription> getDescriptionsList() {
		return descriptionsList;
	}
	

	public List<CustomerOptionDescription> getDescriptionsSettoList() {
		if(descriptionsList==null || descriptionsList.size()==0) {
			descriptionsList = new ArrayList<CustomerOptionDescription>(this.getDescriptions());
		} 
		return descriptionsList;

	}

	public String getCustomerOptionType() {
		return customerOptionType;
	}



	public void setCustomerOptionType(String customerOptionType) {
		this.customerOptionType = customerOptionType;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public boolean isPublicOption() {
		return publicOption;
	}



	public void setPublicOption(boolean publicOption) {
		this.publicOption = publicOption;
	}



	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}



	public Integer getSortOrder() {
		return sortOrder;
	}
}
