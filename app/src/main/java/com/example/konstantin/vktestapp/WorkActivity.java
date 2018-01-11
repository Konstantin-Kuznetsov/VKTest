package com.example.konstantin.vktestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

/**
 * Created by Konstantin on 10.01.2018.
 */

public class WorkActivity extends AppCompatActivity {

    public static final String TAG = "VKTestApp";
    private FragmentManager fm;
    private Fragment fragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

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
    }

    protected Fragment createFragment() {
        return new FriendslistFragment();
    }
}
