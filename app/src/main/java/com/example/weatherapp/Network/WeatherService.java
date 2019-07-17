package com.example.weatherapp.Network;

import com.example.weatherapp.Object.Model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherService {

    @Headers("X-RapidAPI-Host: community-open-weather-map.p.rapidapi.com")

    @GET("/weather")
    Observable<Model> getWeather(@Query("units") String unit,
                                 @Query("q") String location);
}
