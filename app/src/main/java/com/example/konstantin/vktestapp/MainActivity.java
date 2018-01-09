package com.example.konstantin.vktestapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "VKTestApp";
    private FragmentManager fm;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);

        // если фрагмент еще не существует - создаем новый
        // иначе - загружаем уже подготовленный
        if (fragment == null) {
            fragment = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

//        получение fingerprints приложения
//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        for (String str: fingerprints) {
//            Log.i("VK fingerprints list", str);
//        }

    }

    protected Fragment createFragment() {
        if (!VKSdk.isLoggedIn()) return new LoginFragment();
        else return new FriendslistFragment();
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
