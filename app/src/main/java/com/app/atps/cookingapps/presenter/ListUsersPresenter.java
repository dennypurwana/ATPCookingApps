package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ListUserActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListUsersPresenter implements ListUserActivityInterface.Presenter {
    private final ListUserActivityInterface.View mView;
    private final Context mContext;


    @Inject
    public ListUsersPresenter(ListUserActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
