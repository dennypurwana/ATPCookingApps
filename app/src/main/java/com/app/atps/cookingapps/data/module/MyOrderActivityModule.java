package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddUserByAdminActivityInterface;
import com.app.atps.cookingapps.view.MyOrderActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class MyOrderActivityModule {

    private final MyOrderActivityInterface.View mView;
    private final Context mContext;

    public MyOrderActivityModule(MyOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    MyOrderActivityInterface.View providesMyOrderActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesMyOrderActivityContext(){
        return mContext;
    }
}
