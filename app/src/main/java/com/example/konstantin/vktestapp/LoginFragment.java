package com.example.konstantin.vktestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vk.sdk.VKSdk;


/**
 * Created by Konstantin on 09.01.2018.
 */

public class LoginFragment extends Fragment {

    public static final String TAG = "VKTestApp";

    Button login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login =  view.findViewById(R.id.buttonLogin);
        login.setOnClickListener((View v) -> loginVK());
    }

    private void loginVK() {
        if (!VKSdk.isLoggedIn()) VKSdk.login(getActivity());
        else Toast.makeText(getContext(), "Already logged in", Toast.LENGTH_LONG).show();
    }
}
