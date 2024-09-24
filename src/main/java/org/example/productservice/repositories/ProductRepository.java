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

    //JPA Query Declared Methods
    Product findFirstByName(String name);

    Product findProductById(long productId);

    List<Product> findAllByCategory(String category);

    Product findFirstByCategory(String category);

    List<Product> findByNameIn(List<String> newProductNames);

    Page<Product> findAll(Pageable pageable);

    //fetching all attributes may not good for efficiency
    //Always we don't want all the columns
    // HQL  Queries, diff SQL variants have diff SQL syntaxes, HQL work with any DB(MYSql, Postgres, Oracle)
    // writing queries in terms of classes
    // more visibility on queries, get attributes you need
    @Query("select p from Product p where p.category = :category and p.name = :name")
    Product getProductWithParticularCategory(@Param("category") String category,
                                             @Param("name") String name);

    //Selecting required columns only by creating Projections with superset class
    @Query("select p.name as title, p.description as desc from Product p where p.category = :category")
    List<ProductProjection> getAllTitleOfProductsGivenCategory(@Param("category") String category);

    // Native Queries, in this case spring blindly execute the queries, much control, with nativeQuery as TRUE
}
