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
import com.app.atps.cookingapps.adapter.MejaAdapter;
import com.app.atps.cookingapps.adapter.UserAdapter;
import com.app.atps.cookingapps.data.component.DaggerListTableRestoActivityComponent;
import com.app.atps.cookingapps.data.module.ListTableRestoActivityModule;
import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.presenter.ListTableRestoPresenter;
import com.app.atps.cookingapps.presenter.ListUsersPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class ListTableRestoActivity extends AppCompatActivity implements ListTableRestoActivityInterface.View,MejaAdapter.MejaAdapterCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @Inject
    MejaAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    @Inject
    ListTableRestoPresenter listTableRestoPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_table_layout);
        ButterKnife.bind(this);
        DaggerListTableRestoActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listTableRestoActivityModule(new ListTableRestoActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_table_resto);
        toolbarTitlePage.setText("List Meja ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listTableRestoPresenter.getAllDataMeja();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnToFormAddMeja)
    public void toAddMeja() {
        Intent intent=new Intent(ListTableRestoActivity.this,AddTableRestoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showMeja(List<Meja> mejaList) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMeja(mejaList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMejaProgress(boolean show) {
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
    public void onMejaClicked(Meja Meja) {

    }
}
