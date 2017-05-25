package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.DashboardActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class DashboardPresenter implements DashboardActivityInterface.Presenter {
    private final DashboardActivityInterface.View mView;
    private final Context mContext;

    @Inject
    public DashboardPresenter(DashboardActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


}
