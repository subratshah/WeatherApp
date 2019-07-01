package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("?q={city},{country}")
    Call<Model> getWeather(@Path("city") String city, @Path("country") String country);
}
