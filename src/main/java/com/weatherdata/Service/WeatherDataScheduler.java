package com.weatherdata.Service;

import com.weatherdata.Model.WeatherData;
import com.weatherdata.Repo.WeatherRepo;
import com.weatherdata.Service.response.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WeatherDataScheduler {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherRepo weatherDataRepository;

    @Scheduled(cron = "${weather.refresh.cron}")
    public void refreshWeatherData() {
        List<String> citiesToRefresh = Arrays.asList("Bishkek", "Moscow", "Oslo");
        for (String city : citiesToRefresh) {
            OpenWeatherResponse updatedData =  weatherService.getWeatherDataByCity(city);
            WeatherData weatherData = weatherDataRepository.findByCity(city);
            WeatherData weatherData1=weatherService.convertToWeatherData(updatedData);
            weatherService.updateWeatherData(weatherData.getId(),weatherData1);
        }
    }
}
