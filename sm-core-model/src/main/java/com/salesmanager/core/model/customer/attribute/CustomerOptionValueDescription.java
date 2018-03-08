package com.salesmanager.core.model.customer.attribute;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name = "CUSTOMER_OPT_VAL_DESCRIPTION", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
			"CUSTOMER_OPT_VAL_ID",
			"LANGUAGE_ID"
		})
	}
)
public class CustomerOptionValueDescription extends Description {
	private static final long serialVersionUID = 7402155175956813576L;
	
	@ManyToOne(targetEntity = CustomerOptionValue.class)
	@JoinColumn(name = "CUSTOMER_OPT_VAL_ID")
	private CustomerOptionValue customerOptionValue;
	
	
	public CustomerOptionValueDescription() {
	}

	public CustomerOptionValue getCustomerOptionValue() {
		return customerOptionValue;
	}

	public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
		this.customerOptionValue = customerOptionValue;
	}

}
