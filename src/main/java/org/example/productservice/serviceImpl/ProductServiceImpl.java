package org.example.productservice.serviceImpl;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(long id) throws ProductNotFoundException{
        if(id > 10)
            throw new ProductNotFoundException("Product with id "+ id + " is not found in the database");
        Product product = new Product();
        product.setId(id);
        product.setName("Product Name");
        product.setDescription("Product Description");
        return product;
    }

    @Override
    public Product getProductByCategory(String category) throws CategoryNotFoundException {
        if(category.equals("Product"))
            throw new CategoryNotFoundException("Category not found");
        return new Product();
    }
}
