package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerRegisterActivityComponent;
import com.app.atps.cookingapps.data.module.RegisterActivityModule;
import com.app.atps.cookingapps.presenter.RegisterPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterActivityInterface.View {
    @Inject
    RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);
        DaggerRegisterActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .registerActivityModule((new RegisterActivityModule(this,this)))
                .build().inject(this);

    }
    @Override
    @OnClick(R.id.loginLink)
    public void toLoginPage() {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
