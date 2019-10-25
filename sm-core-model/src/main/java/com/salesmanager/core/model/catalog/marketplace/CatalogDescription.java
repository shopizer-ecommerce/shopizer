package com.salesmanager.core.model.catalog.marketplace;


import com.salesmanager.core.model.common.description.Description;
import com.salesmanager.core.model.reference.language.Language;


/*@Entity
@Table(name="CATEGORY_DESCRIPTION", schema=SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints={
		@UniqueConstraint(columnNames={
			"CATEGORY_ID",
			"LANGUAGE_ID"
		})
	}
)*/
public class CatalogDescription extends Description {

	

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*	@ManyToOne(targetEntity = Catalog.class)
	@JoinColumn(name = "CATALOG_ID", nullable = false)*/
	private Catalog catalog;


	
	public CatalogDescription() {
	}
	
	public CatalogDescription(String name, Language language) {
		this.setName(name);
		this.setLanguage(language);
		super.setId(0L);
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	

}
