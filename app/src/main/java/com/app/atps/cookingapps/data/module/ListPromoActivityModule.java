package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListPromoActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class ListPromoActivityModule {
    private final ListPromoActivityInterface.View mView;
    private final Context mContext;

    public ListPromoActivityModule(ListPromoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ListPromoActivityInterface.View providesListPromoActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesListPromoActivityContext(){
        return mContext;
    }
}
