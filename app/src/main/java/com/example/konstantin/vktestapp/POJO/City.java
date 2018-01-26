package com.example.konstantin.vktestapp.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class City {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
