package org.example.productservice.inheritance_strategy_entities.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_mentors")
public class Mentor extends  User{
    private double avg_rating;
    private  String current_company;
}
