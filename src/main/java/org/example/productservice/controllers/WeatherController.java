package org.example.productservice.controllers;

import org.example.productservice.exceptions.DataNotFoundException;
import org.example.productservice.models.WeatherInfo;
import org.example.productservice.services.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherInfoService weatherInfoService;

    @GetMapping("/now/{lat}/{lon}")
    public WeatherInfo getCurrentweatherInfo(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        return weatherInfoService.getCurrentWeatherInfo(lat, lon);
    }

    @GetMapping("/forecast/{lat}/{lon}")
    public WeatherInfo getForecastweatherInfo(@PathVariable("lat") double lat, @PathVariable("lon") double lon) throws DataNotFoundException {
        return weatherInfoService.getForecastWeatherInfo(lat, lon);
    }
}
