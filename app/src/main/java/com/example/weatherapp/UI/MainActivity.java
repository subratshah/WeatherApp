package com.example.weatherapp.UI;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.weatherapp.Adapter.ViewPagerAdapter;
import com.example.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cityList = new String[]{"Chennai,in", "Varanasi,in", "Delhi,in", "London,uk", "Paris,it", "Sydney,au"};

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), cityList));
    }
}
