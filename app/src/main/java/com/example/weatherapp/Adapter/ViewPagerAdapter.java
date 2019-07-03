package com.example.weatherapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weatherapp.R;
import com.example.weatherapp.UI.CityFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> childFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, String[] cityList) {
        super(fm);
        for (String city : cityList) {
            childFragments.add(new CityFragment(city));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments.get(position);
    }

    @Override
    public int getCount() {
        return childFragments.size();
    }
}