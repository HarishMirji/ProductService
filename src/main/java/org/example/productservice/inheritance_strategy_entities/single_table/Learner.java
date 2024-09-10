package org.example.productservice.inheritance_strategy_entities.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("LEARNER")
public class Learner extends User {
    private String college;
    private String company;
}
