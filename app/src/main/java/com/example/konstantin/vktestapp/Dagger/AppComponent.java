package com.example.konstantin.vktestapp.Dagger;

import com.example.konstantin.vktestapp.Presenters.FriendsDetailsPresenter;
import com.example.konstantin.vktestapp.Presenters.FriendsListPresenter;
import com.example.konstantin.vktestapp.UI.FriendsDetailsFragment;
import com.example.konstantin.vktestapp.UI.FriendsListAdapter;
import com.example.konstantin.vktestapp.UI.FriendsListFragment;
import com.example.konstantin.vktestapp.UI.WorkActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Konstantin on 15.01.2018.
 */

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class, UtilsModule.class})
public interface AppComponent {

    void inject(FriendsListPresenter friendslistPresenter);
    void inject(FriendsDetailsPresenter newsListPresenter);

    void inject(FriendsListFragment newsListFragment);
    void inject(FriendsDetailsFragment newsDetailsFragment);

    void inject(WorkActivity workActivity);

    void inject(FriendsListAdapter friendsListAdapter);
}
