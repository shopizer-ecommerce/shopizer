package com.salesmanager.core.model.catalog.catalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name="CATALOG_ENTRY")
public class CatalogEntry extends SalesManagerEntity<Long, CatalogEntry> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "SEQ_NAME", value = "CATALOG_ENT_SEQ_NEXT_VAL"),
        @Parameter(name = "INITIAL_VALUE", value = "4"),
        @Parameter(name = "INCREMENT_SIZE", value = "1")
        }
    )
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    Product product;
 
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    Category category;
    
	@ManyToOne
	@JoinColumn(name = "CATALOG_ID", nullable = false)
	private Catalog catalog;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

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

}
