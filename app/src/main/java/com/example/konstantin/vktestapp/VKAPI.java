package com.example.konstantin.vktestapp;

import com.example.konstantin.vktestapp.POJO.FriendsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Konstantin on 26.01.2018.
 */

public class VKAPI {
    public interface VKApiInterface {
        @GET("friends.get") // запрос списка друзей авторизованного пользователя с указанными ниже параметрами
        Call<FriendsList> getFriends(
                @Query("user_id") String userID,
                @Query("fields") String fields, // запрашиваемые поля
                @Query("lang") int lang, // ru (0) — русский, en (3) — английский
                //@Query("token") String token, // token необходим для некоторых методов
                @Query("v") String apiVersion
        );

        @GET("users.getNearby") // запрос пользователей, которые находятся неподалеку
        Call<FriendsList> getUsersNearby(
                @Query("user_id") String userID,
                @Query("fields") String fields, // запрашиваемые поля
                @Query("latitude") long latitude, // широта запрашивающего пользователя
                @Query("longitude") long longitude, // долгота запрашивающего пользователя
                @Query("radius") int radius, // 1-300м, 2-2400м, 3-18км, 4-150км. (по умолчанию 1)
                @Query("lang") int lang, // ru (0) — русский, en (3) — английский
                //@Query("token") String token, // token необходим для некоторых методов
                @Query("v") String apiVersion
        );


    }
}
