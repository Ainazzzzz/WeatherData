package com.weatherdata.Service.response;

import lombok.Data;

import java.util.List;
@Data
public class OpenWeatherResponse {
    private String name;
    private List<Weather> weather;
    private String dt;
    private Clouds clouds;
    private String zipCode;
    private Main main;
    @Data
    public static class Weather {
        private long id;
        private String main;
        private String description;
        private String icon;

    }
    @Data
    public static class Clouds {
        private String all;
    }
    @Data
    public static class Main {
        private double temp;
        private double feels_like;


    }
}
