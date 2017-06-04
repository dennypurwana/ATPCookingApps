package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.RegisterActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class RegisterPresenter implements RegisterActivityInterface.Presenter {

    private final RegisterActivityInterface.View mView;
    private final Context mContext;

    @Inject
    public RegisterPresenter(RegisterActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
