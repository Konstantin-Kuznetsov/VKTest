package com.example.konstantin.vktestapp.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konstantin.vktestapp.Application.AppInit;
import com.example.konstantin.vktestapp.Presenters.FriendsListPresenter;
import com.example.konstantin.vktestapp.R;


import javax.inject.Inject;


/**
 * Created by Konstantin on 09.01.2018.
 */

public class FriendsListFragment extends Fragment {

    @Inject FriendsListPresenter presenter;

    public static final String TAG = "VKTestApp";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppInit.getComponent().inject(this);

        presenter.attachView(this);

        // получить список друзей и отобразить в recyclerView
        presenter.getListOfFriends(true); // скачать из сети

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }
}
