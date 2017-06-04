package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerHomeActivityComponent;
import com.app.atps.cookingapps.data.component.DaggerHomeGuestActivityComponent;
import com.app.atps.cookingapps.data.module.HomeActivityModule;
import com.app.atps.cookingapps.data.module.HomeGuestActivityModule;
import com.app.atps.cookingapps.presenter.HomeGuestPresenter;
import com.app.atps.cookingapps.presenter.HomePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class HomeGuestActivity extends AppCompatActivity implements HomeGuestActivityInterface.View {


    @Inject
    HomeGuestPresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_guest_layout);
        ButterKnife.bind(this);
        DaggerHomeGuestActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .homeGuestActivityModule(new HomeGuestActivityModule(this,this))
                .build().inject(this);
    }

    @Override
    public void launcher() {

    }

    @Override
    @OnClick(R.id.layoutButtonMenu)
    public void toMenuPage() {
        Intent intent=new Intent(HomeGuestActivity.this,MenuOrderActivity.class);
        intent.putExtra("flag","NEW");
        intent.putExtra("idOrder","");
        intent.putExtra("namaMeja","");
        intent.putExtra("totalBayar","");
        intent.putExtra("menuMakanList","");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.layoutButtonListPromo)
    public void toPromoPage() {
        Intent intent=new Intent(HomeGuestActivity.this,ListPromoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    @OnClick(R.id.layoutButtonListOrder)
    public void toListOrder() {
        Intent intent=new Intent(HomeGuestActivity.this,ListOrderGuestActivity.class);
        intent.putExtra("userType","GUEST");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
