package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListOrderGuestActivityInterface;
import com.app.atps.cookingapps.view.ListUserActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */
@Module
public class ListOrderGuestActivityModule {

private final ListOrderGuestActivityInterface.View mView;
    private final Context mContext;

    public ListOrderGuestActivityModule(ListOrderGuestActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ListOrderGuestActivityInterface.View providesLisOrderGuestActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesLisOrderGuestActivityContext(){
        return mContext;
    }
}
