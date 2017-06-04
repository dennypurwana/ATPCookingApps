package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Promo;
import com.app.atps.cookingapps.view.ListPromoActivityInterface;
import com.app.atps.cookingapps.view.ListUserActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListPromoPresenter implements ListPromoActivityInterface.Presenter {
    private final ListPromoActivityInterface.View mView;
    private final Context mContext;

    FirebaseDatabase firebase;
    DatabaseReference promoRef;

    @Inject
    public ListPromoPresenter(FirebaseDatabase firebase,ListPromoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase=firebase;
        promoRef = this.firebase.getReference("promo");
    }

    @Override
    public void getAllPromo() {
        mView.showProgress(true);
        promoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data promo : ", dataSnapshot.toString());
                List<Promo> promos=new ArrayList<Promo>();
                Promo promo=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    promo = singleSnapshot.getValue(Promo.class);
                    promos.add(promo);
                }
                if (promos.size()>0) {
                    mView.showProgress(false);
                    mView.showPromo(promos);
                }else {
                    mView.showProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
