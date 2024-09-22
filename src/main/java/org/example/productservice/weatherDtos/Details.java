package org.example.productservice.weatherDtos;

import lombok.Data;

import java.util.List;

@Data
public class Details {
    private long dt;
    private List<Weather> weather;
    private MainDetails main;
    private Cloud clouds;
    private Wind wind;
    private int visibility;
    private double pop;
    private String dt_txt;
}
