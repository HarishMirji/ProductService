package org.example.productservice.services;

import org.example.productservice.exceptions.DataNotFoundException;
import org.example.productservice.models.WeatherInfo;

public interface WeatherInfoService {

    WeatherInfo getCurrentWeatherInfo(double lat, double lon);
    WeatherInfo getForecastWeatherInfo(double lat, double lon) throws DataNotFoundException;

}
