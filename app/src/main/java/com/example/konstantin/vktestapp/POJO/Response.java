package com.example.konstantin.vktestapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class Response {
    @SerializedName("count")
    private int count;
    @SerializedName("items")
    private List<UserData> items = null;

    public int getCount() {
        return count;
    }

    public List<UserData> getItems() {
        return items;
    }
}
