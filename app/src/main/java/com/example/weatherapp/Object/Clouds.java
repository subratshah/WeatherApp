package com.example.weatherapp.Object;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}