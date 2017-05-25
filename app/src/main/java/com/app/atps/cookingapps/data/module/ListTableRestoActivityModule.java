package com.app.atps.cookingapps.data.module;

import android.content.Context;

import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListTableRestoActivityInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emerio on 5/25/17.
 */

@Module
public class ListTableRestoActivityModule {
private final ListTableRestoActivityInterface.View mView;
private  final Context mContext;

    public ListTableRestoActivityModule(ListTableRestoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @CustomScope
    @Provides
    ListTableRestoActivityInterface.View providesListTableRestoActivityInterfaceView(){
        return mView;
    }

    @CustomScope
    @Provides
    Context providesListTableRestoActivityContext(){
        return mContext;
    }
}

