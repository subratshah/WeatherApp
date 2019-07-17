package com.example.weatherapp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableField;

import com.example.weatherapp.Network.ServiceManager;
import com.example.weatherapp.Object.Model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WeatherViewModel implements LifecycleObserver {

    private ServiceManager serviceManager;
    List<String> cities = new ArrayList<>();

    public ObservableField<String> cityText = new ObservableField<>();
    public ObservableField<String> tempText = new ObservableField<>();
    public ObservableField<String> conditionText = new ObservableField<>();
    public ObservableField<String> pressureText = new ObservableField<>();
    public ObservableField<String> humidityText = new ObservableField<>();
    public ObservableField<String> conditionImageUrl = new ObservableField<>();

    @Inject
    public WeatherViewModel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void init() {
        cities.add("Chennai,in");
        cities.add("Varanasi,in");
        cities.add("London,uk");
        cities.add("Sydney,au");

        onPageSwipe(0);
    }

    public void onPageSwipe(int position) {
        serviceManager.getWeather("metric", cities.get(position)).subscribe(this::populateView);
    }

    public void populateView(Model weather) {
        if (weather != null) {
            cityText.set(weather.getName());
            tempText.set(weather.getMain().getTemp().concat("°C"));
            conditionText.set(weather.getWeather().get(0).getMain());
            pressureText.set("Pressure: ".concat(weather.getMain().getPressure()).concat(" bar"));
            humidityText.set("Humidity: ".concat(weather.getMain().getHumidity()).concat("%"));
            conditionImageUrl.set("http://api.openweathermap.org/img/w/" + weather.getWeather().get(0).getIcon() + ".png");
        }
    }
}
