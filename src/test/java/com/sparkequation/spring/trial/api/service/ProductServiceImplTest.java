package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.handler.NotFoundException;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sparkequation.spring.trial.api.TestData.testProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandService brandService;

    @Mock
    private CategoryService categoryService;

    private ProductServiceImpl service;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(productRepository, brandService, categoryService);
    }

    @Test
    public void whenFindByIdThenGetResult() {
        Integer testId = 42;
        when(productRepository.findById(eq(testId))).thenReturn(Optional.of(testProduct));
        Optional<Product> result = Optional.ofNullable(service.findProductById(testId));
        assertEquals(testId, result.get().getId());
        verify(productRepository).findById(eq(testId));
    }

    @Test
    public void whenFindByIdThenFailWithNotFound() {
        Integer testId = 43;
        when(productRepository.findById(eq(testId))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            service.findProductById(testId);
        });
        verify(productRepository).findById(eq(testId));
    }

}
