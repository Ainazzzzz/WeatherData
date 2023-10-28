package com.weatherdata.Service;

import com.weatherdata.Model.Current;
import com.weatherdata.Model.WeatherData;
import com.weatherdata.Repo.WeatherRepo;
import com.weatherdata.Service.response.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherServiceImpl implements WeatherService{
   @Autowired
    private WeatherRepo weatherDataRepository;



    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.apiKey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public OpenWeatherResponse getWeatherDataByCity(String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey;
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
        assert response != null;
        WeatherData weatherData = WeatherData.builder()
                .city(response.getName())
                .temp(response.getMain().getTemp())
                .description(response.getWeather().get(0).getDescription())
                .feels_like(response.getMain().getFeels_like())
                .build();
        Current current = Current.builder()
                .dt(response.getDt())
                .clouds(response.getClouds().getAll())
                .build();
       weatherData.setCurrent(current);
        weatherDataRepository.save(weatherData);
        return response;
    }

    @Override
    public OpenWeatherResponse getWeatherDataByZipCode(String zipCode) {
        String url = apiUrl + "?zip=" + zipCode + "&appid=" + apiKey;
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
        assert response != null;
        WeatherData weatherData = WeatherData.builder()
                .city(response.getName())
                .temp(response.getMain().getTemp())
                .description(response.getWeather().get(0).getDescription())
                .feels_like(response.getMain().getFeels_like())
                .build();
        Current current = Current.builder()
                .dt(response.getDt())
                .clouds(response.getClouds().getAll())
                .build();
        weatherData.setCurrent(current);
        weatherDataRepository.save(weatherData);

        return response;
    }

    @Override
    public WeatherData convertToWeatherData(OpenWeatherResponse response) {
        return WeatherData.builder()
                .city(response.getName())
                .temp(response.getMain().getTemp())
                .description(response.getWeather().get(0).getDescription())
                .feels_like(response.getMain().getFeels_like())
                .build();
    }

    @Override
    public void updateWeatherData(long id, WeatherData weatherData) {
        WeatherData weatherData1 = weatherDataRepository.findById(id).orElseThrow();
        weatherData1.setCity(weatherData.getCity());
        weatherData1.setTemp(weatherData.getTemp());
        weatherData1.setDescription(weatherData.getDescription());
        weatherData1.setFeels_like(weatherData.getFeels_like());
        weatherDataRepository.save(weatherData1);
    }


}
