package com.sparkequation.spring.trial.api.repository;


import com.sparkequation.spring.trial.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Integer> {

    Optional<Product> findById(Integer id);
}
