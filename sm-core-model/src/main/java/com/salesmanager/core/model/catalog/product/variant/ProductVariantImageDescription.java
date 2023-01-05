package com.salesmanager.core.model.catalog.product.variant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="PRODUCT_VAR_IMAGE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"PRODUCT_VAR_IMAGE_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_var_image_desc_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductVariantImageDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity = ProductVariantImage.class)
	@JoinColumn(name = "PRODUCT_VAR_IMAGE_ID", nullable = false)
	private ProductVariantImage productVariantImage;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	                            
	
	@Column(name="ALT_TAG", length=100)
	private String altTag;


	public String getAltTag() {
		return altTag;
	}

	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}

	public ProductVariantImage getProductVariantImage() {
		return productVariantImage;
	}

	public void setProductVariantImage(ProductVariantImage productVariantImage) {
		this.productVariantImage = productVariantImage;
	}


}
