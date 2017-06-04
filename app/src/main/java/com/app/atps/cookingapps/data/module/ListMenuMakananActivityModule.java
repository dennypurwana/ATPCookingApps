package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListMenuMakananInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class ListMenuMakananActivityModule {
private final ListMenuMakananInterface.View mView;
private final Context mContext;

    public ListMenuMakananActivityModule(ListMenuMakananInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ListMenuMakananInterface.View providesListMenuMakananActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesListMenuMakananActivityContext(){
        return mContext;
    }
}
