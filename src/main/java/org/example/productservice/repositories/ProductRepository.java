package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.example.productservice.repositories.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves the first product found with the specified name.
     *
     * @param name the name of the product to be searched for.
     * @return the first product that matches the given name, or null if no product is found.
     */
    //JPA Query Declared Methods
    Product findFirstByName(String name);

    /**
     * Finds and returns a product by its ID.
     *
     * @param productId The ID of the product to be retrieved.
     * @return The product with the specified ID, or null if no such product exists.
     */
    Product findProductById(long productId);

    /**
     * Finds all products that belong to the specified category.
     *
     * @param category the category of the products to retrieve
     * @return a list of products that belong to the specified category
     */
    List<Product> findAllByCategory(String category);

    /**
     * Finds the first product within the specified category.
     *
     * @param category the category of the product to retrieve
     * @return the first product that belongs to the specified category
     */
    Product findFirstByCategory(String category);

    /**
     * Finds a list of products that match any of the given names.
     *
     * @param newProductNames A list of product names to search for.
     * @return A list of products that match any of the given names.
     */
    List<Product> findByNameIn(List<String> newProductNames);

    /**
     * Retrieves a paginated list of all products.
     *
     * @param pageable the pagination and sorting information
     * @return a paginated list of products
     */
    Page<Product> findAll(Pageable pageable);

    //fetching all attributes may not good for efficiency
    //Always we don't want all the columns
    // HQL  Queries, diff SQL variants have diff SQL syntaxes, HQL work with any DB(MYSql, Postgres, Oracle)
    // writing queries in terms of classes
    // more visibility on queries, get attributes you need
    @Query("select p from Product p where p.category = :category and p.name = :name")
    Product getProductWithParticularCategory(@Param("category") String category, @Param("name") String name);

    //Selecting required columns only by creating Projections with superset class
    @Query("select p.name as title, p.description as desc from Product p where p.category = :category")
    List<ProductProjection> getAllTitleOfProductsGivenCategory(@Param("category") String category);

    // Native Queries, in this case spring blindly execute the queries, much control, with nativeQuery as TRUE
}
