package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.view.DetailMenuMakananActivityInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

/**
 * Created by emerio on 6/1/17.
 */

public class DetailMenuMakananPresenter implements DetailMenuMakananActivityInterface.Presenter {
    private  final DetailMenuMakananActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference menuRef;

    @Inject
    public DetailMenuMakananPresenter(FirebaseDatabase firebase,DetailMenuMakananActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        menuRef = this.firebase.getReference("menuMasakan");
    }

    @Override
    public void delete(String makananId) {
        menuRef.child(makananId).removeValue();
        mView.toMakananList();

    }
}
