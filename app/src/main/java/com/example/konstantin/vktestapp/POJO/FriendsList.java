package com.example.konstantin.vktestapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class FriendsList {
    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public List<UserData> getFriendsList() {
        return getResponse().getItems();
    }
}
