package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.handler.NotFoundException;
import com.sparkequation.spring.trial.api.handler.NullParameterException;
import com.sparkequation.spring.trial.api.handler.ValidationException;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final String ID_PARAMETR_NAME = "id";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void save(Product product) {
        validateCategories(product);

        modifyProduct(product);
    }

    @Override
    public void update(Product product) {
        validateCategories(product);
        checkNotNull(product.getId());
        Optional<Product> oldProduct = productRepository.findById(product.getId());
        product.setId(oldProduct.orElseThrow(() -> new NotFoundException(product.getId())).getId());
        modifyProduct(product);
    }


    @Override
    public void delete(Integer id) {
        checkNotNull(id);
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        product.setBrand(null);
        product.setCategories(null);
        productRepository.delete(product);

    }

    private void checkNotNull(Object o) {
        if (o == null) {
            throw new NullParameterException(ID_PARAMETR_NAME);
        }
    }

    private void validateCategories(Product product) {
        if (
                product.getCategories().size() < 1
                        || product.getCategories().size() > 5) {
            throw new ValidationException(String.format("%d categories", product.getCategories().size()));
        }
    }

    private void modifyProduct(Product product) {
        Set<Category> categories = new HashSet<>();
        for (Category category : product.getCategories()) {
            categories.add(categoryService.findOrCreate(category.getName()));
        }

        product.setCategories(categories);
        product.setBrand(
                brandService.findOrCreate(product.getBrand().getName(), product.getBrand().getCountry())
        );
        if (product.getExpirationDate().before(Date.valueOf(LocalDate.now().plusDays(30)))) {
            throw new ValidationException("expiration date must be greater than today by 30 days");
        }

        if (product.getRating() > 8.0) {
            product.setFeatured(true);
        }

        productRepository.save(product);
    }

}
