package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ReportActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class ReportActivityModule {
    private final ReportActivityInterface.View mView;
    private final Context mContext;

    public ReportActivityModule(ReportActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @CustomScope
    @Provides
    ReportActivityInterface.View providesReportActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesReportActivityContext(){
        return mContext;
    }
}
