package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.DetailOrderActivityInterface;
import com.app.atps.cookingapps.view.MyOrderActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/27/17.
 */

@Module
public class DetailOrderActivityModule {

    private final DetailOrderActivityInterface.View mView;
    private final Context mContext;

    public DetailOrderActivityModule(DetailOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    DetailOrderActivityInterface.View providesDetailOrderActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesDetailOrderActivityContext(){
        return mContext;
    }
}
