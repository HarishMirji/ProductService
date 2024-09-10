package org.example.productservice.inheritance_strategy_entities.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("TA")
public class TA extends User {
    private int no_of_questions;
    private String college;
}
