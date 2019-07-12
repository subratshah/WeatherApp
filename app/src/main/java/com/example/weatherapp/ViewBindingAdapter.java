package com.example.weatherapp;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewBindingAdapter {

    @BindingAdapter("onPageChangeListener")
    public static void onPageChange(ViewPager viewPager, final PageSelectionListener pageSelectionListener) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                pageSelectionListener.onPageSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @BindingAdapter("fetchImage")
    public static void fetchImage(ImageView imageView, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_cloud_off_black_24dp)
                .into(imageView);
    }

    @FunctionalInterface
    public interface PageSelectionListener {
        void onPageSelected(int position);
    }
}
