package com.salesmanager.core.model.order.orderaccount;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.utils.CloneUtils;

@Entity
@Table (name="ORDER_ACCOUNT_PRODUCT" )
public class OrderAccountProduct implements Serializable {
	private static final long serialVersionUID = -7437197293537758668L;

	@Id
	@Column (name="ORDER_ACCOUNT_PRODUCT_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
		pkColumnValue = "ORDERACCOUNTPRODUCT_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long orderAccountProductId;

	@ManyToOne
	@JoinColumn(name = "ORDER_ACCOUNT_ID" , nullable=false)
	private OrderAccount orderAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_PRODUCT_ID" , nullable=false)
	private OrderProduct orderProduct;

	@Temporal(TemporalType.DATE)
	@Column (name="ORDER_ACCOUNT_PRODUCT_ST_DT" , length=0 , nullable=false)
	private Date orderAccountProductStartDate;

	@Temporal(TemporalType.DATE)
	@Column (name="ORDER_ACCOUNT_PRODUCT_END_DT", length=0)
	private Date orderAccountProductEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column (name="ORDER_ACCOUNT_PRODUCT_EOT"  , length=0 )
	private Date orderAccountProductEot;

	@Temporal(TemporalType.DATE)
	@Column (name="ORDER_ACCOUNT_PRODUCT_ACCNT_DT"  , length=0 )
	private Date orderAccountProductAccountedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column (name="ORDER_ACCOUNT_PRODUCT_L_ST_DT"  , length=0 )
	private Date orderAccountProductLastStatusDate;

	@Column (name="ORDER_ACCOUNT_PRODUCT_L_TRX_ST" , nullable=false )
	private Integer orderAccountProductLastTransactionStatus;

	@Column (name="ORDER_ACCOUNT_PRODUCT_PM_FR_TY" , nullable=false )
	private Integer orderAccountProductPaymentFrequencyType;

	@Column (name="ORDER_ACCOUNT_PRODUCT_STATUS" , nullable=false )
	private Integer orderAccountProductStatus;

	public OrderAccountProduct() {
	}

	public Long getOrderAccountProductId() {
		return orderAccountProductId;
	}

	public void setOrderAccountProductId(Long orderAccountProductId) {
		this.orderAccountProductId = orderAccountProductId;
	}

	public OrderAccount getOrderAccount() {
		return orderAccount;
	}

	public void setOrderAccount(OrderAccount orderAccount) {
		this.orderAccount = orderAccount;
	}

	public OrderProduct getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(OrderProduct orderProduct) {
		this.orderProduct = orderProduct;
	}

	public Date getOrderAccountProductStartDate() {
		return CloneUtils.clone(orderAccountProductStartDate);
	}

	public void setOrderAccountProductStartDate(Date orderAccountProductStartDate) {
		this.orderAccountProductStartDate = CloneUtils.clone(orderAccountProductStartDate);
	}

	public Date getOrderAccountProductEndDate() {
		return CloneUtils.clone(orderAccountProductEndDate);
	}

	public void setOrderAccountProductEndDate(Date orderAccountProductEndDate) {
		this.orderAccountProductEndDate = CloneUtils.clone(orderAccountProductEndDate);
	}

	public Date getOrderAccountProductEot() {
		return CloneUtils.clone(orderAccountProductEot);
	}

	public void setOrderAccountProductEot(Date orderAccountProductEot) {
		this.orderAccountProductEot = CloneUtils.clone(orderAccountProductEot);
	}

	public Date getOrderAccountProductAccountedDate() {
		return CloneUtils.clone(orderAccountProductAccountedDate);
	}

	public void setOrderAccountProductAccountedDate(
			Date orderAccountProductAccountedDate) {
		this.orderAccountProductAccountedDate = CloneUtils.clone(orderAccountProductAccountedDate);
	}

	public Date getOrderAccountProductLastStatusDate() {
		return CloneUtils.clone(orderAccountProductLastStatusDate);
	}

	public void setOrderAccountProductLastStatusDate(
			Date orderAccountProductLastStatusDate) {
		this.orderAccountProductLastStatusDate = CloneUtils.clone(orderAccountProductLastStatusDate);
	}

	public Integer getOrderAccountProductLastTransactionStatus() {
		return orderAccountProductLastTransactionStatus;
	}

	public void setOrderAccountProductLastTransactionStatus(
			Integer orderAccountProductLastTransactionStatus) {
		this.orderAccountProductLastTransactionStatus = orderAccountProductLastTransactionStatus;
	}

	public Integer getOrderAccountProductPaymentFrequencyType() {
		return orderAccountProductPaymentFrequencyType;
	}

	public void setOrderAccountProductPaymentFrequencyType(
			Integer orderAccountProductPaymentFrequencyType) {
		this.orderAccountProductPaymentFrequencyType = orderAccountProductPaymentFrequencyType;
	}

	public Integer getOrderAccountProductStatus() {
		return orderAccountProductStatus;
	}

	public void setOrderAccountProductStatus(Integer orderAccountProductStatus) {
		this.orderAccountProductStatus = orderAccountProductStatus;
	}
}
