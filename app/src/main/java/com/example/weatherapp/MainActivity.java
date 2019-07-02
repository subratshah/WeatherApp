package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Network.Model;
import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView cityText;
    private TextView tempText;
    private TextView conditionText;
    private TextView pressureText;
    private TextView humidityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = findViewById(R.id.city_text);
        tempText = findViewById(R.id.temp_text);
        conditionText = findViewById(R.id.condition_text);
        pressureText = findViewById(R.id.pressure_text);
        humidityText = findViewById(R.id.humidity_text);

        getWeather();
    }

    private void getWeather() {
        try {
            Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
            WeatherService weatherService = retrofit.create(WeatherService.class);

            Call<Model> call = weatherService.getWeather("metric", "Chennai,in");
            call.enqueue(new Callback<Model>() {

                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                    if (response.body() != null) {
                        cityText.setText(response.body().getName());
                        tempText.setText(String.valueOf(response.body().getMain().getTemp()).concat("°C"));
                        pressureText.setText("Pressure: ".concat(String.valueOf(response.body().getMain().getPressure())));
                        humidityText.setText("Humidity: ".concat(String.valueOf(response.body().getMain().getHumidity())));
                        conditionText.setText("Conditions: ".concat(response.body().getWeather().get(0).getMain()));
                    }
                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
