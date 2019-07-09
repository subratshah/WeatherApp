package com.example.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mCities;

    ViewPagerAdapter(Context context, List<String> cities) {
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.common_layout, container, false);

        WeatherViewModel viewModel = new WeatherViewModel(view);
        viewModel.getWeather(mCities.get(position));

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
}
