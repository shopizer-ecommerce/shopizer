package com.salesmanager.core.model.customer.attribute;

import javax.persistence.Column;
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

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name="CUSTOMER_ATTRIBUTE", schema=SchemaConstant.SALESMANAGER_SCHEMA,
	uniqueConstraints={
		@UniqueConstraint(columnNames={
				"OPTION_ID",
				"CUSTOMER_ID"
			})
	}
)
public class CustomerAttribute extends SalesManagerEntity<Long, CustomerAttribute> {
	private static final long serialVersionUID = -6537491946539803265L;
	
	@Id
	@Column(name = "CUSTOMER_ATTRIBUTE_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUST_ATTR_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OPTION_ID", nullable=false)
	private CustomerOption customerOption;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OPTION_VALUE_ID", nullable=false)
	private CustomerOptionValue customerOptionValue;
	
	@Column(name="CUSTOMER_ATTR_TXT_VAL")
	private String textValue;


	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	
	public CustomerAttribute() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}



	public CustomerOption getCustomerOption() {
		return customerOption;
	}

	public void setCustomerOption(CustomerOption customerOption) {
		this.customerOption = customerOption;
	}

	public CustomerOptionValue getCustomerOptionValue() {
		return customerOptionValue;
	}

	public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
		this.customerOptionValue = customerOptionValue;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public String getTextValue() {
		return textValue;
	}


}
