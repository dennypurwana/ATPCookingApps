package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListUserActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */
@Module
public class ListUserActivityModule {

private final ListUserActivityInterface.View mView;
    private final Context mContext;

    public ListUserActivityModule(ListUserActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ListUserActivityInterface.View providesListUserActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesListUserActivityContext(){
        return mContext;
    }
}
