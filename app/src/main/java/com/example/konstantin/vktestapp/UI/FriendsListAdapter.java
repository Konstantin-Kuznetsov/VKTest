package com.example.konstantin.vktestapp.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.konstantin.vktestapp.Application.AppInit;
import com.example.konstantin.vktestapp.R;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.model.VKApiUserFull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantin on 15.01.2018.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    @Inject Context context;

    private List<VKApiUserFull> friendsListData;
    private OnItemClickListener listener;
    private static final String TAG = "VKTestApp";

    public interface OnItemClickListener { // интерфейс листенера
        void onItemClick(VKApiUserFull item);
    }

    public FriendsListAdapter(OnItemClickListener listener) {
        this.listener = listener;
        AppInit.getComponent().inject(this);
    }

    // передаем массив с актуальными данными
    public void setOrUpdateDataset (@NonNull List<VKApiUserFull> friendsList) {
        this.friendsListData = friendsList;
        Log.i(TAG, "Список контактов обновлен: " + friendsListData.size() + " элементов");
    }

    // создание "хранилица" для элемента с данными
    @Override
    public FriendsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_friend_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendsListAdapter.ViewHolder holder, int position) {

        // обработка клика на элементе
        holder.bind(friendsListData.get(position), listener, position);

        // дата рождения друга
        holder.dateOfBirth.setText(friendsListData.get(position).bdate);

        // полное имя друга
        holder.fullName.setText(friendsListData.get(position).first_name + " " + friendsListData.get(position).last_name);

        // аватарка
        // подгрузка иконки с типом погоды
        Picasso.with(context)
                .load(friendsListData.get(position).photo_max_orig)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return friendsListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView dateOfBirth;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            dateOfBirth = itemView.findViewById(R.id.dateOfBirth);
            avatar = itemView.findViewById(R.id.imageUserAvatar);
        }

        // обработка нажатия на элемент списка
        public void bind(final VKApiUserFull item, final OnItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
