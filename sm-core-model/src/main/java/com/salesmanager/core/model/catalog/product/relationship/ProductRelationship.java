package com.salesmanager.core.model.catalog.product.relationship;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

@Entity
@Table(name = "PRODUCT_RELATIONSHIP")
public class ProductRelationship extends SalesManagerEntity<Long, ProductRelationship> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRODUCT_RELATIONSHIP_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_RELATION_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	@ManyToOne(targetEntity = MerchantStore.class)
	@JoinColumn(name="MERCHANT_ID",nullable=false)  
	private MerchantStore store;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name="PRODUCT_ID",updatable=false,nullable=true) 
	private Product product = null;
	
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name="RELATED_PRODUCT_ID",updatable=false,nullable=true) 
	private Product relatedProduct = null;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="ACTIVE")
	private boolean active = true;
	
	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public Product getRelatedProduct() {
		return relatedProduct;
	}



	public void setRelatedProduct(Product relatedProduct) {
		this.relatedProduct = relatedProduct;
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



	public ProductRelationship() {
	}



	public MerchantStore getStore() {
		return store;
	}

	public void setStore(MerchantStore store) {
		this.store = store;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




}
