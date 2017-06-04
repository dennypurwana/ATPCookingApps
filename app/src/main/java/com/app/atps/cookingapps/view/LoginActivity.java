package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerLoginActivityComponent;
import com.app.atps.cookingapps.data.component.NetComponent;
import com.app.atps.cookingapps.data.module.LoginActivityModule;
import com.app.atps.cookingapps.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityInterface.View {
    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.username)
    EditText email_;
    @BindView(R.id.password)
    EditText password_;

    @BindView(R.id.progress)
    ProgressBar progressBar;

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
    public void toDashboardPage() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    @OnClick(R.id.btnLogin)
    public void submit() {
        if (email_.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field email/username tidak boleh kosong",Toast.LENGTH_LONG).show();
        }
        else if(password_.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field password tidak boleh kosong",Toast.LENGTH_LONG).show();
        }else {
            loginPresenter.getUserByEmailPassword(email_.getText().toString(),password_.getText().toString());
        }
    }

    @Override
    public void resultLogin(boolean status) {
         if(status){
             Toast.makeText(getApplicationContext(),"Anda berhasil login",Toast.LENGTH_LONG).show();
             toDashboardPage();
         }else{

             Toast.makeText(getApplicationContext(),"Please check your email/password",Toast.LENGTH_LONG).show();
         }
    }

    @Override
    public void resultError(String message) {
        Toast.makeText(getApplicationContext(),""+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
