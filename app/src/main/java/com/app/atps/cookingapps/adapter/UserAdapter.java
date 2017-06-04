package com.app.atps.cookingapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    private List<User> listUser;
    private UserAdapterCallback mCallback;
    @Inject
    public UserAdapter() {
        listUser = new ArrayList<>();
    }
    public void setUser(List<User> listUser) {
        this.listUser.addAll(listUser);
    }
    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_user,
                parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User User = listUser.get(position);
        holder.setUser(User);
        holder.userName_.setText(User.getNama());
        holder.email.setText(User.getEmail());
        holder.phone_.setText(User.getPhone());
    }
    private void simpleDateFormat(String date){

    }
    public void setCallback(UserAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userName)
        TextView userName_;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.phone)
        TextView phone_;

        User User;
        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setUser(User user) {
            this.User = user;
        }
        @OnClick(R.id.row_layout)
        void onItemClicked(View view) {
            if (mCallback != null) mCallback.onUserClicked(User);
        }
    }

    public static interface UserAdapterCallback {
        public void onUserClicked(User user);
    }
}
