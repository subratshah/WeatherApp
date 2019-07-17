package com.example.weatherapp;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weatherapp.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    @Inject
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(weatherViewModel);

        activityMainBinding.viewPager.setAdapter(viewPagerAdapter);
        activityMainBinding.setViewmodel(weatherViewModel);

        this.getLifecycle().addObserver(weatherViewModel);
    }
}
