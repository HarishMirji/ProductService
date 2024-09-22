package org.example.productservice;

import org.example.productservice.projections.ProductProjection;
import org.example.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testing(){
        List<ProductProjection> reaponse = productRepository.getAllTitleOfProductsGivenCategory("Electronics");
        System.out.println(reaponse.get(0).getTitle());
        System.out.println(reaponse.get(0).getDesc());
    }

}
