package com.example.weatherapp.Network;

import com.example.weatherapp.Object.Model;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ServiceManager {

    WeatherService weatherService;

    @Inject
    public ServiceManager(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Observable<Model> getWeather(String city, String units) {
        return weatherService.getWeather(city, units).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
