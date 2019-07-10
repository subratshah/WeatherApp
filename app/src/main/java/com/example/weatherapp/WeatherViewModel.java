package com.example.weatherapp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableField;

import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;
import com.example.weatherapp.Object.Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherViewModel implements LifecycleObserver {
    private WeatherService weatherService;
    private Model weather = new Model();
    List<String> cities = new ArrayList<>();

    public ObservableField<String> cityText = new ObservableField<>();
    public ObservableField<String> tempText = new ObservableField<>();
    public ObservableField<String> conditionText = new ObservableField<>();
    public ObservableField<String> pressureText = new ObservableField<>();
    public ObservableField<String> humidityText = new ObservableField<>();
    public ObservableField<String> conditionImageUrl = new ObservableField<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void init() {
        Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
        weatherService = retrofit.create(WeatherService.class);

        cities.add("Chennai,in");
        cities.add("Varanasi,in");
        cities.add("London,uk");
        cities.add("Sydney,au");

        onPageSwipe(0);
    }

    public void onPageSwipe(int position) {
        Call<Model> call = weatherService.getWeather("metric", cities.get(position));
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body() != null) {
                    weather = response.body();

                    cityText.set(weather.getName());
                    tempText.set(weather.getMain().getTemp().concat("°C"));
                    conditionText.set(weather.getWeather().get(0).getMain());
                    pressureText.set("Pressure: ".concat(weather.getMain().getPressure()).concat(" bar"));
                    humidityText.set("Humidity: ".concat(weather.getMain().getHumidity()).concat("%"));
                    conditionImageUrl.set("http://api.openweathermap.org/img/w/" + weather.getWeather().get(0).getIcon() + ".png");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
    }



}
