package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;
import com.app.atps.cookingapps.view.AddPromoActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class AddPromoActivityModule {

    private final AddPromoActivityInterface.View mView;
    private final Context mContext;

    public AddPromoActivityModule(AddPromoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    AddPromoActivityInterface.View providesAddMenuMasakanActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesAddMenuMasakanActivityContext(){
        return mContext;
    }
}
