package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerDashboardActivityComponent;
import com.app.atps.cookingapps.data.module.DashboardActivityModule;
import com.app.atps.cookingapps.presenter.DashboardPresenter;
import com.app.atps.cookingapps.presenter.ListMenuMakananPresenter;
import com.app.atps.cookingapps.presenter.ListTableRestoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    @OnClick(R.id.btnToListMasakan)
    public void toListMenuMakananPage() {
        Intent intent=new Intent(DashboardActivity.this,ListMenuMakananActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.btnToListUser)
    public void toUsersPage() {
        Intent intent=new Intent(DashboardActivity.this,ListUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
@OnClick(R.id.btnToTableResto)
    public void toTableRestaurantPage() {
        Intent intent=new Intent(DashboardActivity.this,ListTableRestoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.btnToReportSummary)
    public void toReportSummaryPage() {
        Intent intent=new Intent(DashboardActivity.this,ReportListAdminActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.btnToPromoList)
    public void toPromotionPage() {
        Intent intent=new Intent(DashboardActivity.this,ListPromoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.btnToNotificationList)
    public void toListNotificationPage() {
        Intent intent=new Intent(DashboardActivity.this,ListNotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
