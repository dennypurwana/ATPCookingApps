package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddTableRestoActivity;
import com.app.atps.cookingapps.view.AddTableRestoActivityInterface;
import com.app.atps.cookingapps.view.AddUserByAdminActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class AddTableRestoActivityModule {

    private final AddTableRestoActivityInterface.View mView;
    private final Context mContext;

    public AddTableRestoActivityModule(AddTableRestoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    AddTableRestoActivityInterface.View providesAddTableRestoActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesAddTableRestonActivityContext(){
        return mContext;
    }
}
