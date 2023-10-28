package com.weatherdata.Model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String zipCode;
    private double temp;
    private double feels_like;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Current current;
}
