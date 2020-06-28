package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Brand;

public interface BrandService {
    Brand findOrCreate(String name, String country);
}
