package org.example.productservice.services;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;

public interface ProductService {

    Product getProductById(long id) throws ProductNotFoundException;
    Product getProductByCategory(String id) throws CategoryNotFoundException;
}
