package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ListPromoActivityInterface;
import com.app.atps.cookingapps.view.ListUserActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListPromoPresenter implements ListUserActivityInterface.Presenter {
    private final ListPromoActivityInterface.View mView;
    private final Context mContext;


    @Inject
    public ListPromoPresenter(ListPromoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
