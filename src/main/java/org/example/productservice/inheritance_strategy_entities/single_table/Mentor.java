package org.example.productservice.inheritance_strategy_entities.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MENTOR")
public class Mentor extends User {
    private double avg_rating;
    private  String current_company;
}
