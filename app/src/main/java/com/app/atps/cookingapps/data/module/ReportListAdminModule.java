package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListOrderGuestActivityInterface;
import com.app.atps.cookingapps.view.ReportListAdminActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */
@Module
public class ReportListAdminModule {

private final ReportListAdminActivityInterface.View mView;
    private final Context mContext;

    public ReportListAdminModule(ReportListAdminActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ReportListAdminActivityInterface.View providesReportListtActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesReportListActivityContext(){
        return mContext;
    }
}
