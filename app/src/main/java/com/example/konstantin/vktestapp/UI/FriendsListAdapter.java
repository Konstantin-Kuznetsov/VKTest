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
import com.example.konstantin.vktestapp.POJO.UserData;
import com.example.konstantin.vktestapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantin on 15.01.2018.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    @Inject Context context;

    private List<UserData> friendsListData;
    private OnItemClickListener listener;
    private static final String TAG = "VKTestApp";

    public interface OnItemClickListener { // интерфейс листенера
        void onItemClick(UserData item);
    }

    public FriendsListAdapter(OnItemClickListener listener) {
        this.listener = listener;
        AppInit.getComponent().inject(this);
    }

    // передаем массив с актуальными данными
    public void setOrUpdateDataset (@NonNull List<UserData> friendsList) {
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


        // полное имя друга
        holder.fullName.setText(friendsListData.get(position).getFirstName() + " " + friendsListData.get(position).getLastName());

        // город проживания
        if (friendsListData.get(position).getCity() != null) {
            holder.city.setText(friendsListData.get(position).getCity().getTitle());
        } else holder.city.setVisibility(View.GONE);

        // город рождения
        if (friendsListData.get(position).getHomeTown() != null) {
            holder.hometown.setText(" родной город: " + friendsListData.get(position).getHomeTown());
        } else holder.hometown.setVisibility(View.GONE);

        // дата рождения
        if (friendsListData.get(position).getBdate() != null) {
            holder.birthdate.setText(friendsListData.get(position).getBdate());
        } else holder.birthdate.setVisibility(View.GONE);

        // занятость
        if (friendsListData.get(position).getOccupation() != null) {
            holder.occupation.setText(friendsListData.get(position).getOccupation().getName());
        } else holder.occupation.setVisibility(View.GONE);


        // аватарка
        // подгрузка иконки с аватаркой пользователя
        Picasso.with(context)
                .load(friendsListData.get(position).getPhotoMaxOrig())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return friendsListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView city;
        private TextView hometown;
        private TextView birthdate;
        private TextView occupation;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.fullName);
            city = itemView.findViewById(R.id.city);
            hometown = itemView.findViewById(R.id.hometown);
            birthdate = itemView.findViewById(R.id.birthdate);
            occupation = itemView.findViewById(R.id.occupation);

            avatar = itemView.findViewById(R.id.imageUserAvatar);
        }

        // обработка нажатия на элемент списка
        public void bind(final UserData item, final OnItemClickListener listener, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
