package com.app.atps.cookingapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerDashboardActivityComponent;
import com.app.atps.cookingapps.data.module.DashboardActivityModule;
import com.app.atps.cookingapps.presenter.DashboardPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by emerio on 5/25/17.
 */

public class DashboardActivity extends AppCompatActivity implements DashboardActivityInterface.View {
    @Inject
    DashboardPresenter dashboardPresenter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        ButterKnife.bind(this);
        DaggerDashboardActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .dashboardActivityModule(new DashboardActivityModule(this,this))
                .build().inject(this);

    }
}
