package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerHomeActivityComponent;
import com.app.atps.cookingapps.data.module.HomeActivityModule;
import com.app.atps.cookingapps.presenter.HomePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class HomeActivity extends AppCompatActivity implements HomeActivityInterface.View {


    @Inject
    HomePresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        ButterKnife.bind(this);

        /*dagger*/
        DaggerHomeActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .homeActivityModule((new HomeActivityModule(this,this)))
                .build().inject(this);


    }

    @Override
    public void launcher() {

    }

    @Override
    @OnClick(R.id.layoutButtonWaiters)
    public void toWaitersPage() {
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        intent.putExtra("userType","WAITERS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.layoutButtonAdmin)
    public void toAdminPage() {
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        intent.putExtra("userType","ADMIN");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override

    @OnClick(R.id.layoutButtonChef)
    public void toChefPage() {
        Intent intent=new Intent(HomeActivity.this,ListOrderGuestActivity.class);
        intent.putExtra("userType","CHEF");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.layoutButtonGuest)
    public void toGuestPage() {
        Intent intent=new Intent(HomeActivity.this,HomeGuestActivity.class);
        intent.putExtra("userType","GUEST");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
