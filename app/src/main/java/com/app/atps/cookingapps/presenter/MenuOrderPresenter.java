package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.view.MenuOrderActivityInterface;
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
 * Created by emerio on 5/28/17.
 */

public class MenuOrderPresenter implements MenuOrderActivityInterface.Presenter {
    private final MenuOrderActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference menuRef;
    List<MenuMakanan> menuMakananList;
    @Inject
    public MenuOrderPresenter(FirebaseDatabase firebase,MenuOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        menuRef = this.firebase.getReference("menuMasakan");
        menuMakananList=new ArrayList<MenuMakanan>();

    }

    @Override
    public void getAllMenuMakanan() {
        mView.showMenuMakananProgress(true);
        menuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data menu makanan : ", dataSnapshot.toString());
                MenuMakanan menu=null;
                menuMakananList.clear();
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

    @Override
    public void getMenuMakananByKategori(String kategoriId) {
          mView.showMenuMakananProgress(true);
        Query query = menuRef.orderByChild("idKategori").equalTo(kategoriId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data menu makanan : ", dataSnapshot.toString());
                MenuMakanan menu=null;
                final ArrayList<MenuMakanan> arrayMenu;
                arrayMenu=new ArrayList<>();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    menu = singleSnapshot.getValue(MenuMakanan.class);
                    arrayMenu.add(menu);
                }
                Log.d("menu makan size : ",String.valueOf(arrayMenu.size()));
                if (arrayMenu.size()>0) {
                    mView.showMenuMakananProgress(false);
                    mView.showMenuMakanan(arrayMenu);

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
