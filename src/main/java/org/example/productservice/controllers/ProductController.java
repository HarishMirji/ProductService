package org.example.productservice.controllers;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * Service for managing products.
     * <p>
     * This ProductService enables the creation, retrieval, updating, and deletion of Product entities.
     * It is used within the ProductController to handle HTTP requests related to product operations,
     * including handling product categories and bulk creations.
     */
    @Autowired
    private ProductService productService;

    /**
     * Creates a new product based on the provided product information.
     *
     * @param product The product object containing the details of the product to be created.
     * @return The newly created product.
     */
    @PostMapping("/create")
    public Product createproduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    /**
     * Updates an existing product identified by the given ID.
     *
     * @param id      the ID of the product to update
     * @param product the product data to update
     * @return the updated product
     * @throws ProductNotFoundException if no product with the specified ID is found
     */
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id, product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     * @return the deleted Product
     * @throws ProductNotFoundException if the product with the specified ID is not found
     */
    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return A ResponseEntity object containing a list of Product objects.
     * @throws ProductNotFoundException if no products are found.
     */
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts() throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The unique identifier of the product to be retrieved.
     * @return The product associated with the specified ID.
     * @throws ProductNotFoundException if no product is found for the provided ID.
     */
    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    /**
     * Retrieves a list of products belonging to a given category.
     *
     * @param category The category of products to retrieve.
     * @return A ResponseEntity containing a list of products in the specified category.
     * @throws CategoryNotFoundException If the specified category does not exist.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.getProductByCategory(category.trim()), HttpStatusCode.valueOf(200));
    }

    /**
     * Creates multiple products in bulk.
     *
     * @param products A list of products to be created.
     * @return A ResponseEntity containing a message about the bulk creation status.
     */
    @PostMapping("/createbulk")
    public ResponseEntity<String> createBulk(@RequestBody List<Product> products) {
        return new ResponseEntity<>(productService.createBulk(products), HttpStatusCode.valueOf(200));
    }

    /**
     * Retrieves a paginated list of all products.
     *
     * @param pageSize the number of products to be displayed per page
     * @param pageNum  the page number to display
     * @return a ResponseEntity containing a Page of Product objects
     */
    @GetMapping()
    public ResponseEntity<Page<Product>> getAllproducts(
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
        return ResponseEntity.ok(productService.getAllproducts(pageSize, pageNum));
    }
}
