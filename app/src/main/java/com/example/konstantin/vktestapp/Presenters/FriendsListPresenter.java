package com.example.konstantin.vktestapp.Presenters;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.konstantin.vktestapp.Application.AppInit;
import com.example.konstantin.vktestapp.POJO.FriendsList;
import com.example.konstantin.vktestapp.POJO.UserData;
import com.example.konstantin.vktestapp.R;
import com.example.konstantin.vktestapp.UI.FriendsListAdapter;
import com.example.konstantin.vktestapp.UI.FriendsListFragment;
import com.example.konstantin.vktestapp.VKAPI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.vk.sdk.VKAccessToken;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Konstantin on 15.01.2018.
 */

public class FriendsListPresenter {

    @Inject
    Context context;
    @Inject
    VKAPI.VKApiInterface api;
    @Inject
    Gson gson;



    private static final String TAG = "VKTestApp";
    public static final String FIELDS_VK = "first_name,last_name,sex,bdate,country,city,home_town,photo_max_orig,occupation";
    public static final int LANG_CONST_VK = 0;
    public static final String VERSION_API_VK = "5.71";

    private WeakReference<FriendsListFragment> bindedFragment;
    private RecyclerView friendsRecycler;

    public FriendsListPresenter() {
        AppInit.getComponent().inject(this);
    }

    // Прикрепление и открепление активити в зависимости от ЖЦ
    public void attachView(@NonNull FriendsListFragment view) {
        bindedFragment = new WeakReference<FriendsListFragment>(view);
        if (bindedFragment.get() != null) {
            friendsRecycler = bindedFragment.get().getView().findViewById(R.id.friendsListRecycler);

        }
    }

    public void detachView() {
        bindedFragment = null;
        friendsRecycler = null;
    }

    private void configureRecyclerView(List<UserData> friends) {

        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(new FriendsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserData item) {
                // создаем новый фрагмент и згружаем данные
                Log.i(TAG, item.getFirstName() + " " + item.getLastName());

                // TODO: открытие фрагмента с подробной информацией
                //openFriendsDetailsFragment(item.getId());
            }
        });

        friendsRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        friendsRecycler.setAdapter(friendsListAdapter);
        friendsListAdapter.setOrUpdateDataset(friends);
    }

    // updateList: true - обновить с сервера, false вернуть закешированное
    public void getListOfFriends(boolean updateList) {
        if (updateList) {
            // обновить из сети
            // запрос списка друзей с информацией из конкретных полей
            Call<FriendsList> callFriendsList = api.getFriends(
                    // нужно задать конкретный userID, иначе будет использован ID авторизованного пользователя(если передаем токен в запросе)
                    VKAccessToken.currentToken().userId,
                    FIELDS_VK,
                    LANG_CONST_VK,
                    VERSION_API_VK);

            callFriendsList.enqueue(new Callback<FriendsList>() {
                @Override
                public void onResponse(Call<FriendsList> call, Response<FriendsList> response) {
                    if (response.isSuccessful()) {
                        FriendsList friendsList = response.body();
                        configureRecyclerView(friendsList.getFriendsList());

                    } else {
                        handleError(call, response);
                    }
                }

                @Override
                public void onFailure(Call<FriendsList> call, Throwable t) {

                }
            });

//            FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(context);
//
//            locationProviderClient.getLastLocation()
//                    .addOnSuccessListener(context, new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            // Got last known location. In some rare situations this can be null.
//                            if (location != null) {
//                                // Logic to handle location object
//                            }
//                        }
//                    });

        } else {
            // закешированное
        }
    }

    private String getToken() {
        return VKAccessToken.currentToken().accessToken;
    }

    private void handleError(Call<FriendsList> call, Response<FriendsList> response) {
        // TODO сделать корректную обработку ошибок обращения к серверу
    }

}
