package com.salesmanager.core.model.catalog.catalog;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@EntityListeners(value = com.salesmanager.core.model.common.audit.AuditListener.class)
@Table(name = "CATALOG_ENTRY",uniqueConstraints=
@UniqueConstraint(columnNames = {"CATEGORY_ID", "CATALOG_ID"}) )
public class CatalogCategoryEntry extends SalesManagerEntity<Long, CatalogCategoryEntry> implements Auditable {
	
	
    @Embedded
    private AuditSection auditSection = new AuditSection();
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, 
	generator = "TABLE_GEN")
	
	@TableGenerator(name = "TABLE_GEN", 
	table = "SM_SEQUENCER", 
	pkColumnName = "SEQ_NAME",
	valueColumnName = "SEQ_COUNT",
	allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, 
	initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE,
	pkColumnValue = "CATALOG_ENT_SEQ_NEXT_VAL")
	private Long id;
 
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    Category category;
    
	@ManyToOne
	@JoinColumn(name = "CATALOG_ID", nullable = false)
	private Catalog catalog;
	
	//TODO d products ????
	
    @Column(name = "VISIBLE")
    private boolean visible;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

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
	public void setAuditSection(AuditSection audit) {
		auditSection = audit;
		
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
