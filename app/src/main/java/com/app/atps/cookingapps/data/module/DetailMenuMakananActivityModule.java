package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;
import com.app.atps.cookingapps.view.DetailMenuMakananActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class DetailMenuMakananActivityModule {

    private final DetailMenuMakananActivityInterface.View mView;
    private final Context mContext;

    public DetailMenuMakananActivityModule(DetailMenuMakananActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    DetailMenuMakananActivityInterface.View providesDetailMenuMakananActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesDetailMenuMakananActivityContext(){
        return mContext;
    }
}
