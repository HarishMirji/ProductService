package org.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.productservice.weatherDtos.City;
import org.example.productservice.weatherDtos.Coord;
import org.example.productservice.weatherDtos.Details;
import org.example.productservice.weatherDtos.Weather;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherInfo {

    private Coord coord;
    private List<Weather> weather;
    private City city;
    private List<Details> list;
}
