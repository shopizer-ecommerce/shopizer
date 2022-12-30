package com.salesmanager.core.model.catalog.product.variant;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name = "PRODUCT_VAR_IMAGE")
public class ProductVariantImage extends SalesManagerEntity<Long, ProductVariantImage> {


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRODUCT_VAR_IMAGE_ID")
	@TableGenerator(name = "TABLE_GEN", 
	table = "SM_SEQUENCER", 
	pkColumnName = "SEQ_NAME", 
	valueColumnName = "SEQ_COUNT", 
	pkColumnValue = "PRD_VAR_IMG_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@Column(name = "PRODUCT_IMAGE")
	private String productImage;
	
	@Column(name = "DEFAULT_IMAGE")
	private boolean defaultImage = true;
	
	@ManyToOne(targetEntity = ProductVariantGroup.class)
	@JoinColumn(name = "PRODUCT_VARIANT_GROUP_ID", nullable = false)
	private ProductVariantGroup productVariantGroup;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productVariantImage", cascade = CascadeType.ALL)
	private Set<ProductVariantImageDescription> descriptions = new HashSet<ProductVariantImageDescription>();

	public ProductVariantImage(){
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public boolean isDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(boolean defaultImage) {
		this.defaultImage = defaultImage;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Set<ProductVariantImageDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<ProductVariantImageDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public ProductVariantGroup getProductVariantGroup() {
		return productVariantGroup;
	}

	public void setProductVariantGroup(ProductVariantGroup productVariantGroup) {
		this.productVariantGroup = productVariantGroup;
	}


}
