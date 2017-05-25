package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ListTableRestoActivity;
import com.app.atps.cookingapps.view.ListTableRestoActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListTableRestoPresenter implements ListTableRestoActivityInterface.Presenter {
    private final ListTableRestoActivityInterface.View mView;
    private final Context mContext;

    @Inject
    public ListTableRestoPresenter(ListTableRestoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
