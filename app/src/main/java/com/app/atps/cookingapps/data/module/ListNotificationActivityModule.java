package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListNotificationActivityInterface;
import com.app.atps.cookingapps.view.ListPromoActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class ListNotificationActivityModule {
    private final ListNotificationActivityInterface.View mView;
    private final Context mContext;

    public ListNotificationActivityModule(ListNotificationActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }


    @CustomScope
    @Provides
    ListNotificationActivityInterface.View providesListNotificationActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesListNotificationActivityContext(){
        return mContext;
    }
}
