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
import com.app.atps.cookingapps.adapter.PromoAdapter;
import com.app.atps.cookingapps.data.component.DaggerListPromoActivityComponent;
import com.app.atps.cookingapps.data.module.ListPromoActivityModule;
import com.app.atps.cookingapps.model.Promo;
import com.app.atps.cookingapps.presenter.ListMenuMakananPresenter;
import com.app.atps.cookingapps.presenter.ListPromoPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class ListPromoActivity extends AppCompatActivity implements ListPromoActivityInterface.View,PromoAdapter.PromoAdapterCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @Inject
    PromoAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    @Inject
    ListPromoPresenter listPromoPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_promo_layout);
        ButterKnife.bind(this);
        DaggerListPromoActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listPromoActivityModule(new ListPromoActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_info);
        toolbarTitlePage.setText("List Promo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listPromoPresenter.getAllPromo();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onPromoClicked(Promo Promo) {

    }

    @Override
    @OnClick(R.id.btnToFormAddPromo)
    public void toFormAddPromo() {
        Intent intent=new Intent(ListPromoActivity.this,AddPromoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showPromo(List<Promo> prmoList) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setPromo(prmoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
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
}
