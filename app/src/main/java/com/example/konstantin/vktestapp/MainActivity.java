package com.example.konstantin.vktestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "VKTestApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        получение fingerprints приложения

//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        for (String str: fingerprints) {
//            Log.i("VK fingerprints list ", str);
//        }


        // запрос авторизации
        VKSdk.login(this);

    }

    // обработка результатов автотризации приложения пользователем
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                Log.i(TAG, "Пользователь успешно авторизовался");
            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Log.i(TAG, "Произошла ошибка авторизации (например, пользователь запретил авторизацию)");

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}