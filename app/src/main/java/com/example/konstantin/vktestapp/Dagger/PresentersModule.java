package com.example.konstantin.vktestapp.Dagger;

import android.support.annotation.NonNull;

import com.example.konstantin.vktestapp.Presenters.FriendsDetailsPresenter;
import com.example.konstantin.vktestapp.Presenters.FriendsListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Konstantin on 15.01.2018.
 */

@Module
public class PresentersModule {
    @Provides
    @NonNull
    @Singleton
    public FriendsListPresenter provideNewsListPresenter() {
        return new FriendsListPresenter();
    }

    @Provides
    @NonNull
    @Singleton
    public FriendsDetailsPresenter provideNewsDetailsPresenter() {
        return new FriendsDetailsPresenter();
    }
}
