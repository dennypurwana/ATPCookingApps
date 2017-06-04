package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.MenuMakananAdapter;
import com.app.atps.cookingapps.adapter.UserAdapter;
import com.app.atps.cookingapps.data.component.DaggerListUserActivityComponent;
import com.app.atps.cookingapps.data.module.ListUserActivityModule;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.presenter.ListUsersPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class ListUserActivity extends AppCompatActivity implements ListUserActivityInterface.View,UserAdapter.UserAdapterCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;

    @Inject
    ListUsersPresenter listUsersPresenter;
    @Inject
    UserAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.progress)
    ProgressBar mProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_user_layout);
        ButterKnife.bind(this);
        DaggerListUserActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listUserActivityModule(new ListUserActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_user);
        toolbarTitlePage.setText("List Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listUsersPresenter.getAllUser();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnToFormAddUser)
    public void toAddUser() {
        Intent intent=new Intent(ListUserActivity.this,AddUserByAdminActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void showUser(List<User> userList) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setUser(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUserProgress(boolean show) {
        if (show && adapter.getItemCount() == 0) {
            mProgress.setVisibility(View.VISIBLE);
        } else {
            mProgress.setVisibility(View.GONE);
        }

    }

    @Override
    public void showEmptyMessage() {
        mTextEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onUserClicked(User user) {

    }
}
