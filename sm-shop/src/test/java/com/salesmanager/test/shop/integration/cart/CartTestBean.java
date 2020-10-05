package com.salesmanager.test.shop.integration.cart;

import com.salesmanager.shop.model.catalog.product.ReadableProduct;

import java.util.ArrayList;
import java.util.List;

public class CartTestBean {

    private String cartId;

    private List<ReadableProduct> products = new ArrayList<>();

    public String getCartId() {
        return cartId;
    }

    void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setProducts(List<ReadableProduct> products) {
        this.products = products;
    }

    public List<ReadableProduct> getProducts() {
        return products;
    }

}
