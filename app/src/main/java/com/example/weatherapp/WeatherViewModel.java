package com.example.weatherapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;
import com.example.weatherapp.Object.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherViewModel {
    Model weather = new Model();
    TextView cityText;
    TextView tempText;
    TextView conditionText;
    TextView pressureText;
    TextView humidityText;
    ImageView conditionImage;

    public WeatherViewModel(View view) {
        cityText = view.findViewById(R.id.city_text);
        tempText = view.findViewById(R.id.temp_text);
        conditionText = view.findViewById(R.id.condition_text);
        pressureText = view.findViewById(R.id.pressure_text);
        humidityText = view.findViewById(R.id.humidity_text);
        conditionImage = view.findViewById(R.id.condition_image);
    }

    void getWeather(String location) {
        Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<Model> call = weatherService.getWeather("metric", location);
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body() != null) {
                    weather = response.body();

                    cityText.setText(weather.getName());
                    tempText.setText(response.body().getMain().getTemp().concat("°C"));
                    pressureText.setText("Pressure: ".concat(String.valueOf(response.body().getMain().getPressure())));
                    humidityText.setText("Humidity: ".concat(String.valueOf(response.body().getMain().getHumidity())));
                    conditionText.setText("Condition: ".concat(String.valueOf(response.body().getWeather().get(0).getMain())));
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
    }
}

