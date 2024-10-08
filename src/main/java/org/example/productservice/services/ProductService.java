package org.example.productservice.services;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product deleteProductById(long productId) throws ProductNotFoundException;

    Product updateProductById(long productId, Product product) throws ProductNotFoundException;

    Product getProductById(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Product> getProductByCategory(String category) throws CategoryNotFoundException;

    String createBulk(List<Product> products);

    Page<Product> getAllproducts(int pageSize, int pageNum);

}
