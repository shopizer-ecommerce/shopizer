package com.salesmanager.core.model.catalog.product.variation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table(name = "PRODUCT_VAR_IMAGE")
public class ProductVariationImage extends SalesManagerEntity<Long, ProductVariationImage> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRODUCT_VAR_IMAGE_ID")
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRD_VAR_IMG_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;

	@Column(name = "PRODUCT_IMAGE")
	private String productImage;
	
	@Column(name = "DEFAULT_IMAGE")
	private boolean defaultImage = true;
	
	@ManyToOne(targetEntity = ProductAvailability.class)
	@JoinColumn(name = "PRODUCT_AVAIL_ID", nullable = false)
	private ProductAvailability productAvailability;

	
	//private MultiPartFile image

	public ProductVariationImage(){
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

	public ProductAvailability getProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(ProductAvailability productAvailability) {
		this.productAvailability = productAvailability;
	}

}
