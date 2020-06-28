package com.sparkequation.spring.trial.api.controller;

import com.sparkequation.spring.trial.api.model.Product;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TestData {
    static Product testProduct = Product.builder()
            .id(42)
            .name("Test product")
            .featured(false)
            .expirationDate(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())))
            .itemsInStock(12)
            .rating(12.0)
            .build();

    static List<Product> products = Stream.of(testProduct).collect(Collectors.toList());
}
