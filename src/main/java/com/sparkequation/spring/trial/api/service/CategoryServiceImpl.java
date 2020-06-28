package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Brand;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category findOrCreate(String name) {
        Category category = repository.findByName(name);
        return category != null ? category : repository.save(new Category(name));
    }
}
