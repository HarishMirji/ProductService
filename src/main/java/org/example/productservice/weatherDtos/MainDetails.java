package org.example.productservice.weatherDtos;

import lombok.Data;

@Data
public class MainDetails {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double sea_level;
    private double grnd_level;
    private double humidity;
    private double temp_kf;

}
