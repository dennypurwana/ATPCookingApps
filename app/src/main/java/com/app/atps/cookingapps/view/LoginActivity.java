package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerLoginActivityComponent;
import com.app.atps.cookingapps.data.component.NetComponent;
import com.app.atps.cookingapps.data.module.LoginActivityModule;
import com.app.atps.cookingapps.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityInterface.View {
    @Inject
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        DaggerLoginActivityComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .loginActivityModule((new LoginActivityModule(this,this)))
                .build().inject(this);
    }
    @Override
    @OnClick(R.id.registerLink)
    public void toRegisterPage() {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.btnLogin)
    public void toDashboardPage() {
        Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
