package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.Date;

public class ReadableRecentlyViewedProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String image;
    private Double price;
    private Date viewedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Date getViewedAt() { return viewedAt; }
    public void setViewedAt(Date viewedAt) { this.viewedAt = viewedAt; }
}
