package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.LoginActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class LoginActivityModule {
    private final LoginActivityInterface.View mView;
    private final Context mContext;
    public LoginActivityModule(LoginActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }
    @Provides
    @CustomScope
    LoginActivityInterface.View providesLoginActivityInterfaceView(){
        return mView;
    }
    @Provides
    @CustomScope
    Context providesLoginActivityContext(){
        return mContext;
    }
}

