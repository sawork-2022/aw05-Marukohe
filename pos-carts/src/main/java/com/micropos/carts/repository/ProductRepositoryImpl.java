package com.micropos.carts.repository;

import com.micropos.carts.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {
    private final RestTemplate restTemplate;
    private List<Product> products;

    public ProductRepositoryImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Product getProduct(String productId) {
        List<Product> productList = allProducts();
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private List<Product> allProducts() {
        if (products == null) {
            Product[] products1 = restTemplate.getForObject("http://localhost:8082/api/products/", Product[].class);
            if (products1 == null) {
                products = new ArrayList<>();
            } else {
                products = Arrays.asList(products1);
            }
        }
        return products;
    }
}
