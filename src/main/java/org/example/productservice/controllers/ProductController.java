package org.example.productservice.controllers;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id)  throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{category}")
    public Product getProductByCategory(@PathVariable("category") String category)  throws CategoryNotFoundException {
        return productService.getProductByCategory(category.trim());
    }
}
