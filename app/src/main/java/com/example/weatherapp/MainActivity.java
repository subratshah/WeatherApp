package com.example.weatherapp;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        WeatherViewModel weatherViewModel = new WeatherViewModel();
        this.getLifecycle().addObserver(weatherViewModel);
        activityMainBinding.setViewModel(weatherViewModel);
    }
}
