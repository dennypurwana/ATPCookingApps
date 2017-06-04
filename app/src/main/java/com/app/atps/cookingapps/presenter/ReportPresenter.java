package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.ReportActivityInterface;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ReportPresenter implements ReportActivityInterface.Presenter {
    private final ReportActivityInterface.View mView;
    private Context mContext;

    @Inject
    public ReportPresenter(ReportActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
}
