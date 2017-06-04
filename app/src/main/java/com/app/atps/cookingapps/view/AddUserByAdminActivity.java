package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerAddUserByAdminActivityComponent;
import com.app.atps.cookingapps.data.module.AddUserByAdminActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.presenter.AddUserByAdminPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class AddUserByAdminActivity extends AppCompatActivity implements AddUserByAdminActivityInterface.View
{

    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;

    //progress
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.titleProgress)
    TextView titleProgressBar;


    //field2
    @BindView(R.id.edTextUsername)
    EditText userName;
    @BindView(R.id.edTextEmail)
    EditText email;
    @BindView(R.id.edTextPhone)
    EditText phone;
    @BindView(R.id.edTextPassword)
    EditText password;

    @Inject
    AddUserByAdminPresenter addUserByAdminPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_tambah_user_by_admin);
        ButterKnife.bind(this);
        DaggerAddUserByAdminActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .addUserByAdminActivityModule(new AddUserByAdminActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_user);
        toolbarTitlePage.setText("Tambah User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public void resultAddUser(boolean status) {
        if (status){
            Toast.makeText(getApplicationContext(),"data berhasil disimpan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"data gagal disimpan",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    @OnClick(R.id.btnSimpanUser)
    public void simpan() {
        if(userName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field username tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(email.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field email  tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(phone.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field phone tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field password tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }

        else
        {
            User user=new User();
            user.setIdUser("");
            user.setNama(userName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPhone(phone.getText().toString());
            user.setPassword(password.getText().toString());
            addUserByAdminPresenter.tambahUser(user,"NEW");
        }

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setText("sedang menyimpan data..");
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        titleProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toListUser() {
        Intent intent=new Intent(AddUserByAdminActivity.this,ListUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
