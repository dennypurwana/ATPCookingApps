package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.view.DetailOrderActivityInterface;
import com.app.atps.cookingapps.view.MyOrderActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by emerio on 5/28/17.
 */

public class DetailOrderPresenter implements DetailOrderActivityInterface.Presenter {
    private final DetailOrderActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference orderRef;

    @Inject
    public DetailOrderPresenter(FirebaseDatabase firebase, DetailOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        orderRef = this.firebase.getReference("order");
    }


    @Override
    public void updateStatusOrder(final String idOrder, final String status) {
        Log.d("string id : ", idOrder);
        orderRef.child(idOrder).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot tasksSnapshot) {
                try {
                    orderRef.child(idOrder).child("statusOrder").setValue(status);
                    mView.toMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
