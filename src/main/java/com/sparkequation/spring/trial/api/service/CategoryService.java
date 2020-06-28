package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Category;

public interface CategoryService {
    Category findOrCreate(String name);
}
