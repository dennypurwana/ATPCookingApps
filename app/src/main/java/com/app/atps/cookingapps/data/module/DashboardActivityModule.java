package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.DashboardActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */
@Module
public class DashboardActivityModule {
    private final DashboardActivityInterface.View mView;
    private final Context mContext;
    public DashboardActivityModule(DashboardActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
    @Provides
    @CustomScope
    DashboardActivityInterface.View providesDashboardActivityInterfaceView(){
        return mView;
    }
    @Provides
    @CustomScope
    Context providesDashboardActivityContext(){
        return mContext;
    }
}
