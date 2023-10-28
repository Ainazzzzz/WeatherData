package com.weatherdata.Controller;

import com.weatherdata.Service.WeatherService;
import com.weatherdata.Service.response.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{city}")
    public OpenWeatherResponse getWeatherByCity(@PathVariable String city) {
        return weatherService.getWeatherDataByCity(city);
    }

    @GetMapping("/zip/{zipCode}")
    public OpenWeatherResponse getWeatherByZipCode(@PathVariable String zipCode) {
        return weatherService.getWeatherDataByZipCode(zipCode);
    }
}
