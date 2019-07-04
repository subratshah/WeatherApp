package com.example.weatherapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Network.Model;
import com.example.weatherapp.Network.RetrofitUtil;
import com.example.weatherapp.Network.WeatherService;
import com.example.weatherapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mCities;
    private TextView cityText;
    private TextView tempText;
    private TextView conditionText;
    private TextView pressureText;
    private TextView humidityText;
    private ImageView conditionImage;

    public ViewPagerAdapter(Context context, List<String> cities) {
        mContext = context;
        mCities = cities;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_city, container, false);

        cityText = view.findViewById(R.id.city_text);
        tempText = view.findViewById(R.id.temp_text);
        conditionText = view.findViewById(R.id.condition_text);
        pressureText = view.findViewById(R.id.pressure_text);
        humidityText = view.findViewById(R.id.humidity_text);
        conditionImage = view.findViewById(R.id.condition_image);

        getWeather(mCities.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    void getWeather(String location) {
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
                cityText.setText(t.getMessage());
            }
        });
    }
}
