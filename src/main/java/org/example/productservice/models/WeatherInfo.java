package org.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.productservice.dto.City;
import org.example.productservice.dto.Coord;
import org.example.productservice.dto.Details;
import org.example.productservice.dto.Weather;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherInfo {

    private Coord coord;
    private List<Weather> weather;
    private City city;
    private List<Details> list;
}
