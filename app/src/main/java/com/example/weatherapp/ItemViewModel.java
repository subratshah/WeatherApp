package com.example.weatherapp;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;
import com.example.weatherapp.Object.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemViewModel extends BaseObservable {
    Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
    WeatherService weatherService = retrofit.create(WeatherService.class);

    public ObservableField<String> cityText = new ObservableField<>();
    public ObservableField<String> tempText = new ObservableField<>();
    public ObservableField<String> conditionText = new ObservableField<>();
    public ObservableField<String> pressureText = new ObservableField<>();
    public ObservableField<String> humidityText = new ObservableField<>();
    public ObservableField<String> conditionImageUrl = new ObservableField<>();

    public ItemViewModel(String city) {
        Call<Model> call = weatherService.getWeather("metric", city);
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body() != null) {
                    cityText.set(response.body().getName());
                    tempText.set(response.body().getMain().getTemp().concat("°C"));
                    conditionText.set(response.body().getWeather().get(0).getMain());
                    pressureText.set("Pressure: ".concat(response.body().getMain().getPressure()).concat(" bar"));
                    humidityText.set("Humidity: ".concat(response.body().getMain().getHumidity()).concat("%"));
                    conditionImageUrl.set("http://api.openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
    }
}
