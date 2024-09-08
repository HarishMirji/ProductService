package org.example.productservice.models;

import lombok.Data;

@Data
public class Product {

    private long id;
    private String name;
    private String description;
    private String category;
}
