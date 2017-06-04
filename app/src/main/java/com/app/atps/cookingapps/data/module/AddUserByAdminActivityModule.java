package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;
import com.app.atps.cookingapps.view.AddUserByAdminActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class AddUserByAdminActivityModule {

    private final AddUserByAdminActivityInterface.View mView;
    private final Context mContext;

    public AddUserByAdminActivityModule(AddUserByAdminActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    AddUserByAdminActivityInterface.View providesAddUserByAdminActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesAddUserByAdminActivityContext(){
        return mContext;
    }
}
