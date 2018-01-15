package com.example.konstantin.vktestapp.Application;

import android.app.Application;

import com.example.konstantin.vktestapp.Dagger.AppComponent;
import com.example.konstantin.vktestapp.Dagger.AppModule;
import com.example.konstantin.vktestapp.Dagger.DaggerAppComponent;
import com.example.konstantin.vktestapp.Dagger.PresentersModule;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * Created by Konstantin on 07.01.2018.
 */

public class AppInit extends Application {

    private static AppComponent component;

    // обработка валидности токена (слетает, если пользователь поменял пароль или нажал кнопку
    // выйти на всех устройствах)
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
            }
        }
    };

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();

        // Инициализация VK SDK
        VKSdk.initialize(this);
        vkAccessTokenTracker.startTracking();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .presentersModule(new PresentersModule())
                .build();
    }
}
