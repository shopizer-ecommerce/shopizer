package com.salesmanager.core.model.catalog.product.review;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_REVIEW", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
		@UniqueConstraint(columnNames={
				"CUSTOMERS_ID",
				"PRODUCT_ID"
			})
		}
)
public class ProductReview extends SalesManagerEntity<Long, ProductReview> implements Auditable {
	private static final long serialVersionUID = -7509351278087554383L;

	@Id
	@Column(name = "PRODUCT_REVIEW_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
	pkColumnValue = "PRODUCT_REVIEW_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@Embedded
	private AuditSection audit = new AuditSection();
	
	@Column(name = "REVIEWS_RATING")
	private Double reviewRating;
	
	@Column(name = "REVIEWS_READ")
	private Long reviewRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REVIEW_DATE")
	private Date reviewDate;
	
	@Column(name = "STATUS")
	private Integer status;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CUSTOMERS_ID")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productReview")
	private Set<ProductReviewDescription> descriptions = new HashSet<ProductReviewDescription>();
	
	public ProductReview() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(Double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Long getReviewRead() {
		return reviewRead;
	}

	public void setReviewRead(Long reviewRead) {
		this.reviewRead = reviewRead;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<ProductReviewDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<ProductReviewDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	@Override
	public AuditSection getAuditSection() {
		return audit;
	}
	
	@Override
	public void setAuditSection(AuditSection audit) {
		this.audit = audit;
	}
	
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

}
