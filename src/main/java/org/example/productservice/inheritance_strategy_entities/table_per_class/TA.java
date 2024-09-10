package org.example.productservice.inheritance_strategy_entities.table_per_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_tas")
public class TA extends User {
    private int no_of_questions;
    private String college;
}
