package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.HomeActivityInterface;
import com.app.atps.cookingapps.view.HomeGuestActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class HomeGuestActivityModule {
    private final HomeGuestActivityInterface.View mView;
    private final Context mContext;
    public HomeGuestActivityModule(HomeGuestActivityInterface.View view, Context context) {
        this.mView = view;
        this.mContext=context;
    }

    @Provides
    @CustomScope
    HomeGuestActivityInterface.View provideHomeActivityInterfaceView(){
        return mView;
    }

    @Provides
    @CustomScope
    Context provideHomeActivityContext(){
        return mContext;
    }

}
