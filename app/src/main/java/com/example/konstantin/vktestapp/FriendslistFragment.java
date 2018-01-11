package com.example.konstantin.vktestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vk.sdk.VKSdk;

/**
 * Created by Konstantin on 09.01.2018.
 */

public class FriendslistFragment extends Fragment {
    private Intent openLoginActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // переход на активити с логином
        openLoginActivity = new Intent(getActivity(), LoginActivity.class);
        // убираем активити из бэкстэка после разлогина
        openLoginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button logout = (Button) view.findViewById(R.id.buttonLogout);
        logout.setOnClickListener((View v) -> logoutVK());
    }

    private void logoutVK() {
        openLoginActivity.putExtra("shouldLogout", true);
        startActivity(openLoginActivity);
    }
}
