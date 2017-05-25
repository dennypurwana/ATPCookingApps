package com.app.atps.cookingapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerListUserActivityComponent;
import com.app.atps.cookingapps.data.module.ListUserActivityModule;
import com.app.atps.cookingapps.presenter.ListUsersPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emerio on 5/25/17.
 */

public class ListUserActivity extends AppCompatActivity implements ListUserActivityInterface.View {

    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;

    @Inject
    ListUsersPresenter listUsersPresenter;


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
    }
}
