package com.salesmanager.core.model.catalog.product.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="PRODUCT_OPTION_DESC", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
	@UniqueConstraint(columnNames={
			"PRODUCT_OPTION_ID",
			"LANGUAGE_ID"
		})
	}
)
public class ProductOptionDescription extends Description {
	private static final long serialVersionUID = -3158504904707188465L;
	
	@ManyToOne(targetEntity = ProductOption.class)
	@JoinColumn(name = "PRODUCT_OPTION_ID", nullable = false)
	private ProductOption productOption;
	
	@Column(name="PRODUCT_OPTION_COMMENT")
	@Type(type = "org.hibernate.type.TextType")
	private String productOptionComment;
	
	public ProductOptionDescription() {
	}
	
	public String getProductOptionComment() {
		return productOptionComment;
	}
	public void setProductOptionComment(String productOptionComment) {
		this.productOptionComment = productOptionComment;
	}

	public ProductOption getProductOption() {
		return productOption;
	}

	public void setProductOption(ProductOption productOption) {
		this.productOption = productOption;
	}
}
