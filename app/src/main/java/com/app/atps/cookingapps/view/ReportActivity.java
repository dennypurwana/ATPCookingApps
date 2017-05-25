package com.app.atps.cookingapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerReportActivityComponent;
import com.app.atps.cookingapps.data.module.ReportActivityModule;
import com.app.atps.cookingapps.presenter.ReportPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emerio on 5/25/17.
 */

public class ReportActivity extends AppCompatActivity implements ReportActivityInterface.View {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @Inject
    ReportPresenter reportPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        ButterKnife.bind(this);
        DaggerReportActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .reportActivityModule(new ReportActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_report);
        toolbarTitlePage.setText("Report Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
