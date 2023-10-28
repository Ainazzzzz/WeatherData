package com.weatherdata.Repo;

import com.weatherdata.Model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherData,Long> {
    @Query("select w from WeatherData w where w.city = ?1")
    WeatherData findByCity(String city);


}
