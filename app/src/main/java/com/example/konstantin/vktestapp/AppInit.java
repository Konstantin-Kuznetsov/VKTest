package com.example.konstantin.vktestapp;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by Konstantin on 07.01.2018.
 */

public class AppInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
