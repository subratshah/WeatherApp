package com.example.weatherapp;

import com.example.weatherapp.Network.Model;
import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherViewModel {

    public Model getWeather(String location) {
        final Model[] body = new Model[0];

        Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<Model> call = weatherService.getWeather("metric", location);
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body() != null) {
                    body[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });

        return body[0];
    }
}
