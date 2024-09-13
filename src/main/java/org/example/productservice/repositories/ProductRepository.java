package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findFirstByName(String name);

    Product findProductById(long productId);

    List<Product> findAllByCategory(String category);

    Product findFirstByCategory(String category);

    List<Product> findByNameIn(List<String> newProductNames);
}
