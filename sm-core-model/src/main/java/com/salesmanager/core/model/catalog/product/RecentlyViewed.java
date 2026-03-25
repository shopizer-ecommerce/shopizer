package com.salesmanager.core.model.catalog.product;

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

import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name = "RECENTLY_VIEWED")
public class RecentlyViewed extends SalesManagerEntity<Long, RecentlyViewed> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RECENTLY_VIEWED_ID", unique = true, nullable = false)
    @TableGenerator(name = "RV_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "RECENTLY_VIEWED_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RV_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VIEWED_AT", nullable = false)
    private Date viewedAt = new Date();

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public Date getViewedAt() { return viewedAt; }
    public void setViewedAt(Date viewedAt) { this.viewedAt = viewedAt; }
}
