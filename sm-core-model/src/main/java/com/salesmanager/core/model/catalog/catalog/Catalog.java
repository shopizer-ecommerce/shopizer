package com.salesmanager.core.model.catalog.catalog;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Allows grouping products and category
 * Catalog
 *      - category 1
 *      - category 2
 *      
 *      - product 1
 *      - product 2
 *      - product 3
 *      - product 4
 *      
 * @author carlsamson
 *
 */

//@Entity
//@EntityListeners(value = com.salesmanager.core.model.common.audit.AuditListener.class)
//@Table(name = "CATALOG", schema= SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints=
//    @UniqueConstraint(columnNames = {"MERCHANT_ID", "CODE"}) )


public class Catalog extends SalesManagerEntity<Long, Catalog> implements Auditable {
    private static final long serialVersionUID = 1L;
    
    //@Id
    //@Column(name = "CATEGORY_ID", unique=true, nullable=false)
    //@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CATALOG_SEQ_NEXT_VAL")
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    //@Embedded
    private AuditSection auditSection = new AuditSection();

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;


    //@Column(name = "SORT_ORDER")
    private Integer sortOrder = 0;


    //@Column(name = "VISIBLE")
    private boolean visible;

    
    //@Column(name="DEFAULT")
    private boolean defaultCatalog;
    
    //@NotEmpty
    //(name="CODE", length=100, nullable=false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Catalog() {
    }
    
    public Catalog(MerchantStore store) {
        this.merchantStore = store;
        this.id = 0L;
    }
    
    @Override
    public Long getId() {
        return this.id;
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


    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }


}