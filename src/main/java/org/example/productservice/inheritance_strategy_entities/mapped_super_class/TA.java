package org.example.productservice.inheritance_strategy_entities.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_TAs")
public class TA extends User{
    private int no_of_questions;
    private String college;
}
