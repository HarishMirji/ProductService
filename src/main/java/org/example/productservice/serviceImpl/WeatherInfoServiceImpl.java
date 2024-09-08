package org.example.productservice.serviceImpl;

import org.example.productservice.exceptions.DataNotFoundException;
import org.example.productservice.models.WeatherInfo;
import org.example.productservice.services.WeatherInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

    public WeatherInfo getCurrentWeatherInfo(double lat, double lon) {
        WeatherInfo weatherInfo = new WeatherInfo();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=43ce3c5aa60af8796a5d764eb85b3cd4";
        weatherInfo = restTemplate.getForObject(url, WeatherInfo.class);
        return weatherInfo;
    }

    @Override
    public WeatherInfo getForecastWeatherInfo(double lat, double lon) throws DataNotFoundException {
        WeatherInfo weatherInfo = new WeatherInfo();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://pro.openweathermap.org/data/2.5/forecast/hourly?lat="+lat+"&lon="+lon+"&appid=43ce3c5aa60af8796a5d764eb85b3cd4";
        try{
            weatherInfo = restTemplate.getForObject(url, WeatherInfo.class);
            return weatherInfo;
        }catch (Exception e){
            throw new DataNotFoundException("Data not found "+ e.getMessage());
        }
    }
}
