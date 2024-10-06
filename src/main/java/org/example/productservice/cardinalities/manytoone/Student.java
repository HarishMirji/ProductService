package org.example.productservice.cardinalities.manytoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
    @Id
    long id;
    String name;
    @ManyToOne
    Batch batch;
}
