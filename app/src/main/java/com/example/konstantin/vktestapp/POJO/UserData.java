package com.example.konstantin.vktestapp.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class UserData {
    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("sex")
    private int sex;
    @SerializedName("city")
    private City city;
    @SerializedName("country")
    private Country country;
    @SerializedName("photo_max_orig")
    private String photoMaxOrig;
    @SerializedName("bdate")
    private String bdate;
    @SerializedName("hidden")
    private int hidden;
    @SerializedName("home_town")
    private String homeTown;
    @SerializedName("occupation")
    private Occupation occupation;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getPhotoMaxOrig() {
        return photoMaxOrig;
    }

    public String getBdate() {
        return bdate;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public Occupation getOccupation() {
        return occupation;
    }
}
