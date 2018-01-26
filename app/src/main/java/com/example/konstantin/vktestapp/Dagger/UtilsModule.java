package com.example.konstantin.vktestapp.Dagger;

import android.support.annotation.NonNull;

import com.example.konstantin.vktestapp.VKAPI;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Konstantin on 26.01.2018.
 */

@Module
public class UtilsModule {

    private static final String BASE_URL = "https://api.vk.com/method/";

    @Provides
    @NonNull
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NonNull
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public VKAPI.VKApiInterface provideVKApi(Retrofit retrofitInstance) {
        return retrofitInstance.create(VKAPI.VKApiInterface.class);
    }
}
