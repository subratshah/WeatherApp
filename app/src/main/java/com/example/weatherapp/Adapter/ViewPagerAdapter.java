package com.example.weatherapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weatherapp.R;
import com.example.weatherapp.UI.CityFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] childFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new CityFragment(R.layout.fragment_city,"Chennai,in"),
                new CityFragment(R.layout.fragment_city,"Delhi,in"),
                new CityFragment(R.layout.fragment_city,"Mumbai,in"),
                new CityFragment(R.layout.fragment_city,"Varanasi,in")
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }
}