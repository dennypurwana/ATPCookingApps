package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ListNotificationActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListNotificationPresenter implements ListNotificationActivityInterface.Presenter {
    private final ListNotificationActivityInterface.View mView;
    private final Context mContext;

    @Inject
    public ListNotificationPresenter(ListNotificationActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
