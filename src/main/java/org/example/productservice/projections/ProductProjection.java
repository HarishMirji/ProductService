package org.example.productservice.projections;

public interface ProductProjection {
    long getId();
    String getTitle();
    String getDesc();
    String getCategory();
}
