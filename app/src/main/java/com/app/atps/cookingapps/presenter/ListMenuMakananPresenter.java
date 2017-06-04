package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.view.ListMenuMakananInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListMenuMakananPresenter implements ListMenuMakananInterface.Presenter {

    private final ListMenuMakananInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference menuRef;

    @Inject
    public ListMenuMakananPresenter(FirebaseDatabase firebase,ListMenuMakananInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        menuRef = this.firebase.getReference("menuMasakan");
    }
    @Override
    public void getAllMenuMakanan() {
        mView.showMenuMakananProgress(true);
        menuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data menu makanan : ", dataSnapshot.toString());
                List<MenuMakanan> menuMakananList=new ArrayList<MenuMakanan>();
                MenuMakanan menu=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    menu = singleSnapshot.getValue(MenuMakanan.class);
                    menuMakananList.add(menu);
                }
                if (menuMakananList.size()>0) {
                    mView.showMenuMakananProgress(false);
                    mView.showMenuMakanan(menuMakananList);
                }else {
                    mView.showMenuMakananProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
