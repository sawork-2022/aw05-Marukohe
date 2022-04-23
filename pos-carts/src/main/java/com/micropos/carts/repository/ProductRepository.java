package com.micropos.carts.repository;

import com.micropos.carts.model.Product;

public interface ProductRepository {
    public Product getProduct(String productId);
}
