package com.example.weatherapp.DependencyInjection;

import com.example.weatherapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityModule {

    //Add all the Activities that need Dagger
    @ContributesAndroidInjector
    abstract MainActivity providesMainActivity();
}
