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

/**
 * Implementation of the ProductService interface that provides various methods
 * for managing products in the repository.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * Creates a new product if a product with the same name does not already exist.
     *
     * @param product The product to be created.
     * @return The existing product if one with the same name is found, otherwise the newly created product.
     */
    @Override
    public Product createProduct(Product product) {
        Product p = productRepository.findFirstByName(product.getName());
        if (p != null) {
            return p;
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product identified by the provided productId.
     *
     * @param productId The ID of the product to be deleted.
     * @return The deleted product if the operation was successful.
     * @throws ProductNotFoundException If no product with the specified productId is found.
     */
    @Override
    public Product deleteProductById(long productId) throws ProductNotFoundException {
        Product p = productRepository.findProductById(productId);
        if (p == null) {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        productRepository.delete(p);
        return p;
    }

    /**
     * Updates an existing product identified by its ID with the provided product details.
     *
     * @param productId the ID of the product to be updated
     * @param product   the product details to update
     * @return the updated product
     * @throws ProductNotFoundException if the product with the specified ID is not found
     */
    @Override
    public Product updateProductById(long productId, Product product) throws ProductNotFoundException {
        Product p = productRepository.findProductById(productId);
        if (p != null) {
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setCategory(product.getCategory());
            productRepository.save(p);
        } else {
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        }
        return p;
    }

    /**
     * Retrieves a product by its ID from the repository.
     *
     * @param id The ID of the product to be retrieved.
     * @return The product with the specified ID.
     * @throws ProductNotFoundException if a product with the specified ID is not found.
     */
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product;
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    /**
     * Retrieves a list of products based on the provided category.
     * If the category does not exist, a CategoryNotFoundException is thrown.
     *
     * @param category the category of the products to retrieve
     * @return a list of products that belong to the specified category
     * @throws CategoryNotFoundException if the specified category is not found
     */
    @Override
    public List<Product> getProductByCategory(String category) throws CategoryNotFoundException {
        Product product = productRepository.findFirstByCategory(category);
        if (product.getCategory() == null) {
            throw new CategoryNotFoundException("Category " + category + " not found");
        }
        return productRepository.findAllByCategory(category);
    }

    /**
     * Adds a list of new products to the repository if they do not already exist.
     *
     * @param products A list of products to be added to the repository.
     * @return A string indicating whether the products were added successfully.
     */
    @Override
    public String createBulk(List<Product> products) {
        products = filterNewProducts(products);
        productRepository.saveAll(products);
        return "Products added successfully";
    }

    /**
     * Retrieves a paginated list of all products sorted by name in descending order
     * and then by category.
     *
     * @param pageSize the number of products per page.
     * @param pageNum  the page number to retrieve.
     * @return a paginated list of products.
     */
    @Override
    public Page<Product> getAllproducts(int pageSize, int pageNum) {
        return productRepository.findAll(
                PageRequest.of(pageNum, pageSize, Sort.by("name").descending().and(Sort.by("category")))
        );
    }

    /**
     * Filters new products by removing those that already exist in the database.
     *
     * @param newProducts A list of products to be filtered.
     * @return A list of products that do not already exist in the database.
     */
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
        return newProducts.stream()
                .filter(product -> !existingProductNames.contains(product.getName()))
                .collect(Collectors.toList());
    }
}