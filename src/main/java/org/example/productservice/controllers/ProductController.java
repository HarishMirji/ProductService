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

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product createproduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id,  @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProductById(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException{
        return productService.deleteProductById(id);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts()  throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") long id)  throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category)  throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.getProductByCategory(category.trim()), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/createbulk")
    public ResponseEntity<String> createBulk(@RequestBody List<Product> products) {
        return new ResponseEntity<>(productService.createBulk(products), HttpStatusCode.valueOf(200));
    }

    @GetMapping()
    public  ResponseEntity<Page<Product>> getAllproducts(
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
        return ResponseEntity.ok(productService.getAllproducts(pageSize, pageNum));
    }
}
