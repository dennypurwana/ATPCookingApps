package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.HomeActivity;
import com.app.atps.cookingapps.view.HomeActivityInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class HomePresenter implements HomeActivityInterface.Presenter {
    HomeActivityInterface.View mView;
    Context mContext;
    @Inject
    public HomePresenter(HomeActivityInterface.View view,Context context){
        this.mView=view;
        this.mContext=context;
    }

}
