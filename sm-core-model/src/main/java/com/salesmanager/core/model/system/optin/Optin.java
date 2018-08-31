package com.salesmanager.core.model.system.optin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "OPTIN", schema= SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints=
@UniqueConstraint(columnNames = {"MERCHANT_ID", "CODE"}))
public class Optin extends SalesManagerEntity<Long, Optin> implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OPTIN_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "OPTIN_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name ="START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name ="END_DATE")
	private Date endDate;
	
	@Column(name="TYPE", nullable=false)
	@Enumerated(value = EnumType.STRING)
	private OptinType optinType;
	
	@ManyToOne(targetEntity = MerchantStore.class)
	@JoinColumn(name="MERCHANT_ID")
	private MerchantStore merchant;
	
	@Column(name="CODE", nullable=false)
	private String code;
	
	@Column(name="DESCRIPTION")
	private String description;


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;	
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public MerchantStore getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantStore merchant) {
		this.merchant = merchant;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OptinType getOptinType() {
		return optinType;
	}

	public void setOptinType(OptinType optinType) {
		this.optinType = optinType;
	}

}
