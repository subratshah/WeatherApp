package com.example.weatherapp.DependencyInjection;

import com.example.weatherapp.Network.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetwrokModule {

    private static final String BASE_URL = "https://community-open-weather-map.p.rapidapi.com";

    @Provides
    @Singleton
    WeatherService providesRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(WeatherService.class);
    }
}
