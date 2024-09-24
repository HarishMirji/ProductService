package org.example.productservice.repositories.projections;

public interface ProductProjection {
    long getId();
    String getTitle();
    String getDesc();
    String getCategory();
}
