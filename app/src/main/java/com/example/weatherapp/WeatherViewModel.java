package com.example.weatherapp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel extends ViewModel implements LifecycleObserver {

    public List<ItemViewModel> itemViewModels = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void init() {
        itemViewModels.add(new ItemViewModel("Chennai,in"));
        itemViewModels.add(new ItemViewModel("Varanasi,in"));
        itemViewModels.add(new ItemViewModel("Delhi,in"));
        itemViewModels.add(new ItemViewModel("Bengaluru,in"));
        itemViewModels.add(new ItemViewModel("Mumbai,in"));
        itemViewModels.add(new ItemViewModel("London,uk"));
        itemViewModels.add(new ItemViewModel("Sydney,au"));

        recyclerAdapter = new RecyclerAdapter(itemViewModels);
    }

    public RecyclerAdapter getAdapter(){
        return recyclerAdapter;
    }
}
