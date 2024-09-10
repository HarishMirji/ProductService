package org.example.productservice.inheritance_strategy_entities.mapped_super_class;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
}
