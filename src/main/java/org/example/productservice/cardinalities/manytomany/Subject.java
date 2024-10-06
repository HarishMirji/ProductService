package org.example.productservice.cardinalities.manytomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity(name = "M2M_Subject")
public class Subject {
    @Id
    long id;
    String name;
    @ManyToMany
    List<Student> students;
}
