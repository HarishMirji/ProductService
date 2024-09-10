package org.example.productservice.inheritance_strategy_entities.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_learners")
public class Learner extends User{
    private String college;
    private String company;
}
