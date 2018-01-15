package com.example.konstantin.vktestapp.Presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.konstantin.vktestapp.Application.AppInit;
import com.example.konstantin.vktestapp.R;
import com.example.konstantin.vktestapp.UI.FriendsListAdapter;
import com.example.konstantin.vktestapp.UI.FriendsListFragment;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantin on 15.01.2018.
 */

public class FriendsListPresenter {

    @Inject Context context;


    private static final String TAG = "VKTestApp";

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

    private void configureRecyclerView(List<VKApiUserFull> friends) {

        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(new FriendsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VKApiUserFull item) {
                // создаем новый фрагмент и згружаем данные
                Log.i(TAG, item.first_name + " " + item.last_name);

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
            VKRequest friendsRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_max_orig, sex, bdate, city, occupation"));
            friendsRequest.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    super.onComplete(response);
                    VKList vkUsersArray = (VKList) response.parsedModel;
                    configureRecyclerView(vkUsersArray);
                }

                @Override
                public void onError(VKError error) {
                    super.onError(error);
                }
            });
        } else {
            // закешированное
        }
    }


}
