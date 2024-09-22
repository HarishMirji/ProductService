package org.example.productservice.weatherDtos;

import lombok.Data;

@Data
public class City {
    private int id;
    private String name;
    private String country;
    private int population;
    private Coord coord;
}
