package org.example.productservice.inheritance_strategy_entities.table_per_class;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "tpc_users")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
}
