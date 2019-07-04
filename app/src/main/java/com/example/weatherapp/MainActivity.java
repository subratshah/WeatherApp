package com.example.weatherapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> cities = new ArrayList<>();
        cities.add("Chennai,in");
        cities.add("Varanasi,in");
        cities.add("Delhi,in");
        cities.add("London,uk");
        cities.add("Paris,it");
        cities.add("Sydney,au");

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(this, cities));
    }
}
