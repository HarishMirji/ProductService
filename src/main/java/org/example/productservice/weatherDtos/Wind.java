package org.example.productservice.weatherDtos;

import lombok.Data;

@Data
public class Wind {
    private int speed;
    private int deg;
    private  double gust;
}
