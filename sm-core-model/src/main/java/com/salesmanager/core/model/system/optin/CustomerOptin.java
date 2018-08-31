package com.salesmanager.core.model.system.optin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;


/**
 * Optin defines optin campaigns for the system.
 * @author carlsamson
 *
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CUSTOMER_OPTIN", schema= SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints=
@UniqueConstraint(columnNames = {"EMAIL", "OPTIN_ID"}))
public class CustomerOptin extends SalesManagerEntity<Long, CustomerOptin> implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOMER_OPTIN_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUST_OPT_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name ="OPTIN_DATE")
	private Date optinDate;

	
	@ManyToOne(targetEntity = Optin.class)
	@JoinColumn(name="OPTIN_ID")
	private Optin optin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	@Column(name="FIRST")
	private String firstName;
	
	@Column(name="LAST")
	private String lastName;
	
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@Column(name="VALUE")
	@Type(type = "org.hibernate.type.TextType")
	private String value;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;	
	}

	public Date getOptinDate() {
		return optinDate;
	}

	public void setOptinDate(Date optinDate) {
		this.optinDate = optinDate;
	}

	public Optin getOptin() {
		return optin;
	}

	public void setOptin(Optin optin) {
		this.optin = optin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

}
