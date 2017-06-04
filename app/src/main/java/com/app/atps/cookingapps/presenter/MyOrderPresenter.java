package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.model.Order;
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

public class MyOrderPresenter implements MyOrderActivityInterface.Presenter {
    private final MyOrderActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference orderRef;

    @Inject
    public MyOrderPresenter(FirebaseDatabase firebase,MyOrderActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        orderRef = this.firebase.getReference("order");
    }

    @Override
    public void order(Order order,String status) {
        mView.showProgressBar();
        if (status.equalsIgnoreCase("UPDATE")){
            DatabaseReference childRef = orderRef.child(order.getIdOrder());
            childRef.setValue(order);
            childRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mView.hideProgressBar();
                    mView.resultOrder(true);
                    mView.toDetailOrder();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    mView.hideProgressBar();
                    mView.resultOrder(false);
                }
            });
        }else {
            String id = orderRef.push().getKey();
            DatabaseReference childRef = orderRef.child(id);
            order.setIdOrder(id);
            childRef.setValue(order);
            childRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mView.hideProgressBar();
                    mView.resultOrder(true);
                    mView.toDetailOrder();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    mView.hideProgressBar();
                    mView.resultOrder(false);
                }
            });
        }
    }
}
