package org.example.productservice.inheritance_strategy_entities.joined_table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "jt_tas")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private int no_of_questions;
    private String college;
}
