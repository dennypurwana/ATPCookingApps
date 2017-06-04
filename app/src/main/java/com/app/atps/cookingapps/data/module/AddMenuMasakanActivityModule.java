package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class AddMenuMasakanActivityModule {

    private final AddMenuMasakanActivityInterface.View mView;
    private final Context mContext;

    public AddMenuMasakanActivityModule(AddMenuMasakanActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    AddMenuMasakanActivityInterface.View providesAddMenuMasakanActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesAddMenuMasakanActivityContext(){
        return mContext;
    }
}
