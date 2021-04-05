package com.salesmanager.core.model.catalog.product;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductDimensions {
  
  
  @Column(name = "LENGTH")
  private BigDecimal length;

  @Column(name = "WIDTH")
  private BigDecimal width;

  @Column(name = "HEIGHT")
  private BigDecimal height;

  @Column(name = "WEIGHT")
  private BigDecimal weight;

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

}
