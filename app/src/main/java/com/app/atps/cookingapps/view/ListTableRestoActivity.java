package com.app.atps.cookingapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerListTableRestoActivityComponent;
import com.app.atps.cookingapps.data.module.ListTableRestoActivityModule;
import com.app.atps.cookingapps.presenter.ListTableRestoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emerio on 5/25/17.
 */

public class ListTableRestoActivity extends AppCompatActivity implements ListTableRestoActivityInterface.View {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;

    @Inject
    ListTableRestoPresenter listTableRestoPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_promo_layout);
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
    }
}
