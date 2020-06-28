package com.sparkequation.spring.trial.api.controller;

import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void createProduct(@Valid @RequestBody
    Product product) {
        productService.save(product);
    }

    @PatchMapping
    public void updateProduct(@RequestBody
                                          Product product){
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.delete(id);
    }
}
