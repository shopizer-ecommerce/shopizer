package com.salesmanager.core.model.catalog.product.type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name = "PRODUCT_TYPE_DESCRIPTION", schema = SchemaConstant.SALESMANAGER_SCHEMA,
    uniqueConstraints = {@UniqueConstraint(columnNames = {"PRODUCT_TYPE_ID", "LANGUAGE_ID"})})

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "product_type_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
//@SequenceGenerator(name = "description_gen", sequenceName = "product_type_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_SEQUENCE_START)
public class ProductTypeDescription extends Description {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @ManyToOne(targetEntity = ProductPrice.class)
  @JoinColumn(name = "PRODUCT_TYPE_ID", nullable = false)
  private ProductType productType;

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

}
