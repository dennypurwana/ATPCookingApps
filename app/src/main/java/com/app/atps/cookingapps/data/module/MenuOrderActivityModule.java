package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.MenuOrderActivity;
import com.app.atps.cookingapps.view.MenuOrderActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/28/17.
 */

@Module
public class MenuOrderActivityModule {

    private  final MenuOrderActivityInterface.View mView;
    private final Context mContext;

    public MenuOrderActivityModule(MenuOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @CustomScope
    @Provides
    MenuOrderActivityInterface.View providesMenuOrderActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesMenuOrderActivityContext(){
        return mContext;
    }
}
