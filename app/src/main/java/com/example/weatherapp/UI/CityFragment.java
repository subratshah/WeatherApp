package com.example.weatherapp.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Network.Model;
import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;
import com.example.weatherapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class CityFragment extends Fragment {
    private TextView cityText;
    private TextView tempText;
    private TextView conditionText;
    private TextView pressureText;
    private TextView humidityText;
    private ImageView conditionImage;
    private String location;

    public CityFragment(String location) {
        this.location = location;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_city, container, false);

        cityText = rootView.findViewById(R.id.city_text);
        tempText = rootView.findViewById(R.id.temp_text);
        conditionText = rootView.findViewById(R.id.condition_text);
        pressureText = rootView.findViewById(R.id.pressure_text);
        humidityText = rootView.findViewById(R.id.humidity_text);
        conditionImage = rootView.findViewById(R.id.condition_image);

        getWeather(location);
        return rootView;
    }

    public void getWeather(String location) {
        Retrofit retrofit = RetrofitUtil.getRetrofitInstance();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<Model> call = weatherService.getWeather("metric", location);
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.body() != null) {
                    cityText.setText(response.body().getName());
                    tempText.setText(String.valueOf(response.body().getMain().getTemp()).concat("°C"));
                    pressureText.setText("Pressure: ".concat(String.valueOf(response.body().getMain().getPressure())));
                    humidityText.setText("Humidity: ".concat(String.valueOf(response.body().getMain().getHumidity())));
                    String condition = response.body().getWeather().get(0).getMain();
                    conditionText.setText("Conditions: ".concat(condition));
                    if (condition.equalsIgnoreCase("Clouds"))
                        conditionImage.setImageResource(R.drawable.ic_wb_cloudy_black_24dp);
                    else if (condition.equalsIgnoreCase("Clear"))
                        conditionImage.setImageResource(R.drawable.ic_wb_sunny_black_24dp);
                    else if (condition.equalsIgnoreCase("Haze"))
                        conditionImage.setImageResource(R.drawable.ic_haze_black_24dp);
                    else
                        conditionImage.setImageResource(R.drawable.ic_cloud_queue_black_24dp);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
    }
}

