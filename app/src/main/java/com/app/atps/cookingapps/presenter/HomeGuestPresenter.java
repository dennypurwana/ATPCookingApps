package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.HomeActivityInterface;
import com.app.atps.cookingapps.view.HomeGuestActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class HomeGuestPresenter implements HomeGuestActivityInterface.Presenter {
    HomeGuestActivityInterface.View mView;
    Context mContext;
    @Inject
    public HomeGuestPresenter(HomeGuestActivityInterface.View view, Context context){
        this.mView=view;
        this.mContext=context;
    }

}
