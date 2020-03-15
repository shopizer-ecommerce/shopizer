package com.salesmanager.core.model.catalog.product.type;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_TYPE", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductType extends SalesManagerEntity<Long, ProductType> implements Auditable {
  private static final long serialVersionUID = 65541494628227593L;

  public final static String GENERAL_TYPE = "GENERAL";

  @Id
  @Column(name = "PRODUCT_TYPE_ID", unique = true, nullable = false)
  @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME",
      valueColumnName = "SEQ_COUNT", pkColumnValue = "PRD_TYPE_SEQ_NEXT_VAL")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
  private Long id;

  @Embedded
  private AuditSection auditSection = new AuditSection();
  
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productType")
  private Set<ProductTypeDescription> descriptions = new HashSet<ProductTypeDescription>();

  @Column(name = "PRD_TYPE_CODE")
  private String code;

  @Column(name = "PRD_TYPE_ADD_TO_CART")
  private Boolean allowAddToCart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MERCHANT_ID", nullable = true)
  private MerchantStore merchantStore;

  public ProductType() {}

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public AuditSection getAuditSection() {
    return auditSection;
  }

  @Override
  public void setAuditSection(AuditSection auditSection) {
    this.auditSection = auditSection;
  }

  public boolean isAllowAddToCart() {
    return allowAddToCart;
  }

  public void setAllowAddToCart(boolean allowAddToCart) {
    this.allowAddToCart = allowAddToCart;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getAllowAddToCart() {
    return allowAddToCart;
  }

  public void setAllowAddToCart(Boolean allowAddToCart) {
    this.allowAddToCart = allowAddToCart;
  }

  public MerchantStore getMerchantStore() {
    return merchantStore;
  }

  public void setMerchantStore(MerchantStore merchantStore) {
    this.merchantStore = merchantStore;
  }


}
