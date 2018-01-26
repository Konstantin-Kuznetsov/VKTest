package com.example.konstantin.vktestapp.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class Occupation {
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
