package org.example.productservice.serviceImpl;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepository;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        Product p = productRepository.findFirstByName(product.getName());
        if(p != null) {
            return p;
        }
        return productRepository.save(product);
    }

    @Override
    public Product deleteProductById(long productId) throws ProductNotFoundException {
        Product p = productRepository.findProductById(productId);
        if(p == null) {
            throw new ProductNotFoundException("Product with id "+ productId+" not found");
        }
        productRepository.delete(p);
        return p;
    }

    @Override
    public Product updateProductById(long productId, Product product) throws ProductNotFoundException {
        Product p = productRepository.findProductById(productId);
        if(p != null) {
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setCategory(product.getCategory());
            productRepository.save(p);
        }
        else {
            throw new ProductNotFoundException("Product with id "+ productId+" not found");
        }
        return p;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException{
        Product product = productRepository.findProductById(id);
        if(product == null) {
            throw new ProductNotFoundException("Product with id "+ id+" not found");
        }
        return product;
    }

    @Override
    public List<Product> getProductByCategory(String category) throws CategoryNotFoundException {
        Product product = productRepository.findFirstByCategory(category);
        if(product.getCategory() == null) {
            throw new CategoryNotFoundException("Category "+category+" not found");
        }
        return productRepository.findAllByCategory(category);
    }

    @Override
    public String createBulk(List<Product> products) {
        products = filterNewProducts(products);
        productRepository.saveAll(products);
        return "Products added successfully";
    }

    @Override
    public Page<Product> getAllproducts(int pageSize, int pageNum) {
        return productRepository.findAll(PageRequest.of(pageNum, pageSize,
                Sort.by("name").descending().and
                        (Sort.by("category"))));
    }

    public List<Product> filterNewProducts(List<Product> newProducts) {
        // Step 1: Extract names from the new products
        List<String> newProductNames = newProducts.stream()
                .map(Product::getName) // Assuming "name" is the unique field
                .collect(Collectors.toList());

        // Step 2: Fetch the products from the database that already exist
        List<Product> existingProducts = productRepository.findByNameIn(newProductNames);

        // Step 3: Extract the names of the existing products
        List<String> existingProductNames = existingProducts.stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        // Step 4: Use streams to filter out products that already exist in the database
        List<Product> filteredNewProducts = newProducts.stream()
                .filter(product -> !existingProductNames.contains(product.getName()))
                .collect(Collectors.toList());

        return filteredNewProducts;
    }
}
