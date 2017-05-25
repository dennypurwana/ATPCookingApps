package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ListMenuMakananInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListMenuMakananPresenter implements ListMenuMakananInterface.Presenter {

    private final ListMenuMakananInterface.View mView;
    private final Context mContext;

    @Inject
    public ListMenuMakananPresenter(ListMenuMakananInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


}
