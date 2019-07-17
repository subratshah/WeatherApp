package com.example.weatherapp.DependencyInjection;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,         //Need to use Dagger in the App
        ActivityModule.class,                        //Need to add Activities that use Dagger
        NetworkModule.class
})
interface AppComponent extends AndroidInjector<WeatherApplication> {

}
