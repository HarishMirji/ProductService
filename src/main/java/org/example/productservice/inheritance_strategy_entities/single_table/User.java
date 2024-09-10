package org.example.productservice.inheritance_strategy_entities.single_table;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "st_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
}
