package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Brand;
import com.sparkequation.spring.trial.api.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository repository;

    @Override
    public Brand findOrCreate(String name, String country) {
        Brand brand = repository.findByNameAndCountry(name, country);
        return brand != null ? brand : repository.save(new Brand(name, country));
    }
}
