package com.salesmanager.core.model.catalog.product.instance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="PRODUCT_INST_IMAGE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"PRODUCT_INST_IMAGE_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_image_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ProductInstanceImageDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity = ProductInstanceImage.class)
	@JoinColumn(name = "PRODUCT_INST_IMAGE_ID", nullable = false)
	private ProductInstanceImage productInstanceImage;
	
	@Column(name="ALT_TAG", length=100)
	private String altTag;

	public ProductInstanceImage getProductInstanceImage() {
		return productInstanceImage;
	}

	public void setProductInstanceImage(ProductInstanceImage productInstanceImage) {
		this.productInstanceImage = productInstanceImage;
	}

	public String getAltTag() {
		return altTag;
	}

	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}


}
