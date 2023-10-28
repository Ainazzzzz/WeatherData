package com.weatherdata.Service;

import com.weatherdata.Model.WeatherData;
import com.weatherdata.Service.response.OpenWeatherResponse;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    OpenWeatherResponse getWeatherDataByCity(String city);
    OpenWeatherResponse getWeatherDataByZipCode(String zipCode);
    WeatherData convertToWeatherData(OpenWeatherResponse response);
    void updateWeatherData(long id,WeatherData weatherData);
}
