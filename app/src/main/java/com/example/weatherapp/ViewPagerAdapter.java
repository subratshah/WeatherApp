package com.example.weatherapp;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.databinding.ViewpagerItemBinding;

public class ViewPagerAdapter extends PagerAdapter {
    private WeatherViewModel weatherViewModel;

    ViewPagerAdapter(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewpagerItemBinding viewpagerItemBinding = (ViewpagerItemBinding) object;
        viewpagerItemBinding.setViewmodel(null);
        container.removeView(viewpagerItemBinding.getRoot());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        ViewpagerItemBinding viewpagerItemBinding = ViewpagerItemBinding.inflate(inflater, container, true);
        viewpagerItemBinding.setViewmodel(weatherViewModel);
        viewpagerItemBinding.executePendingBindings();

        return viewpagerItemBinding;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((ViewpagerItemBinding) object).getRoot() == view;
    }
}
