package com.app.atps.cookingapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerListNotificationActivityComponent;
import com.app.atps.cookingapps.data.module.ListNotificationActivityModule;
import com.app.atps.cookingapps.presenter.ListNotificationPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emerio on 5/25/17.
 */

public class ListNotificationActivity extends AppCompatActivity implements ListNotificationActivityInterface.View {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;

    @Inject
    ListNotificationPresenter listNotificationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_notification_layout);
        ButterKnife.bind(this);
        DaggerListNotificationActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listNotificationActivityModule(new ListNotificationActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_notification);
        toolbarTitlePage.setText("List Notification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
