package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.LoginActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class LoginPresenter implements LoginActivityInterface.Presenter {
    private final LoginActivityInterface.View mView;
    private  final Context context;

    @Inject
    public LoginPresenter(LoginActivityInterface.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }



}
