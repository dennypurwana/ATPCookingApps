package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.RegisterActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class RegisterActivityModule {
    private  final RegisterActivityInterface.View mView;
    private  final Context mContext;

    public RegisterActivityModule(RegisterActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Provides
    @CustomScope
    RegisterActivityInterface.View providesRegisterActivityInterfaceView(){
        return  mView;
    }

    @Provides
    @CustomScope
    Context providesRegisterActivityContext(){
        return mContext;
    }
}
