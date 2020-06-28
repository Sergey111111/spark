package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findProductById(Integer id);
    void save(Product product);

    void update(Product product);

    void delete(Integer id);
}
